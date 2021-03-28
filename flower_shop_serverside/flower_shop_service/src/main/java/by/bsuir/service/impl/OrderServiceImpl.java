package by.bsuir.service.impl;

import by.bsuir.dto.mapper.order.BuyNowOrderMapperDTO;
import by.bsuir.dto.mapper.order.UsualOrderMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.BaseOrderDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.criteria.BuyNowOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderFloristChoiceDTO;
import by.bsuir.dto.model.order.partial.OrderFloristCompletionDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.model.order.usual.UsualOrderDTO;
import by.bsuir.entity.order.BaseOrder;
import by.bsuir.entity.order.OrderFloristInfo;
import by.bsuir.entity.order.OrderProduct;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.order.buynow.BuyNowOrder;
import by.bsuir.entity.order.delivery.DeliveryType;
import by.bsuir.entity.order.usual.UsualOrder;
import by.bsuir.entity.product.Product;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.cart.CartRepository;
import by.bsuir.repository.api.florist.FloristRepository;
import by.bsuir.repository.api.order.BuyNowOrderRepository;
import by.bsuir.repository.api.order.DeliveryTypeRepository;
import by.bsuir.repository.api.order.OrderFloristInfoRepository;
import by.bsuir.repository.api.order.UsualOrderRepository;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final BuyNowOrderMapperDTO buyNowOrderMapper;
    private final UsualOrderMapperDTO usualOrderMapper;

    private final UsualOrderRepository usualOrderRepository;
    private final BuyNowOrderRepository buyNowOrderRepository;

    private final DeliveryTypeRepository deliveryTypeRepository;
    private final OrderFloristInfoRepository orderFloristInfoRepository;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final FloristRepository floristRepository;

    private final CommonServiceHelper commonServiceHelper;

    @Override
    public PageWrapper<UsualOrderDTO> findAll(int page, int size, UsualOrderSearchCriteriaDTO searchParams) {
        Pageable pageable = commonServiceHelper.getPageable(page, size);
        Page<UsualOrder> usualOrders = usualOrderRepository.findAllByClientId(pageable, searchParams.getClientId());

        return
                new PageWrapper<>(
                        usualOrderMapper.toDtoList(usualOrders.toList()),
                        usualOrders.getTotalPages(),
                        usualOrders.getTotalElements());
    }

    @Override
    public PageWrapper<BuyNowOrderDTO> findAll(int page, int size, BuyNowOrderSearchCriteriaDTO searchParams) {
        Pageable pageable = commonServiceHelper.getPageable(page, size);
        Page<BuyNowOrder> usualOrders = buyNowOrderRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        buyNowOrderMapper.toDtoList(usualOrders.toList()),
                        usualOrders.getTotalPages(),
                        usualOrders.getTotalElements());
    }

    @Override
    public BaseOrderDTO findById(Long id) {
        if (usualOrderRepository.existsById(id)) {
            return usualOrderMapper.toDto(usualOrderRepository.getOne(id));
        } else if (buyNowOrderRepository.existsById(id)) {
            return buyNowOrderMapper.toDto(buyNowOrderRepository.getOne(id));
        }
        throw new ResourceNotFoundException("Order with id=" + id + " does not exist!");
    }

    @Override
    @Transactional
    public BaseOrderDTO save(BaseOrderDTO baseOrderDTO) {
        if (baseOrderDTO instanceof UsualOrderDTO) {
            return this.resolveUsualOrderSave((UsualOrderDTO) baseOrderDTO);
        } else if (baseOrderDTO instanceof BuyNowOrderDTO) {
            return this.resolveBuyNowOrderSave((BuyNowOrderDTO) baseOrderDTO);
        } else {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "order_type_mismatch_error",
                    "Order type can`t be resolved");
        }

    }

    private void deleteCart(Long clientId) {
        if (cartRepository.existsByClientId(clientId)) {
            cartRepository.deleteByClientId(clientId);
        } else {
            throw new ResourceNotFoundException("Cart with clientId=" + clientId + " does not found!");
        }
    }

    private BaseOrderDTO resolveUsualOrderSave(UsualOrderDTO usualOrderDTO) {
        UsualOrder usualOrder = usualOrderMapper.toEntity(usualOrderDTO);
        //
        resolveDeliveryType(usualOrder);
        //
        resolveProducts(usualOrder.getOrderProducts());
        //
        UsualOrder savedOrder = usualOrderRepository.save(usualOrder);
        //
        resolveOrderFloristInfo(savedOrder);
        //
        deleteCart(usualOrder.getClientId());
        //
        return usualOrderMapper.toDto(savedOrder);
    }

    private void resolveProducts(List<OrderProduct> orderProducts) {
        orderProducts.forEach(orderProduct -> {
            Product product = productRepository.findById(orderProduct.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Change product quantity, Product with id=" + orderProduct.getProductId() + " does not found!"));
            Integer availableAmount = product.getAvailableAmount() - orderProduct.getQuantity();
            product.setAvailableAmount(availableAmount);
            productRepository.save(product);
        });
    }

    private void resolveOrderFloristInfo(BaseOrder baseOrder) {
        OrderFloristInfo orderFloristInfo = new OrderFloristInfo();
        orderFloristInfo.setOrderId(baseOrder.getId());
        baseOrder.setOrderFloristInfo(orderFloristInfo);
    }

    private void resolveDeliveryType(BaseOrder baseOrder) {
        if (Objects.nonNull(baseOrder.getOrderDeliveryInfo().getDeliveryType().getId())) {
            DeliveryType deliveryType = deliveryTypeRepository.findById(baseOrder.getOrderDeliveryInfo().getDeliveryType().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Delivery type founded by id does not exist!"));
            baseOrder.getOrderDeliveryInfo().setDeliveryType(deliveryType);
        } else if (Objects.nonNull(baseOrder.getOrderDeliveryInfo().getDeliveryType().getDeliveryTypeName())) {
            DeliveryType deliveryType =
                    deliveryTypeRepository.findByDeliveryTypeName(baseOrder.getOrderDeliveryInfo().getDeliveryType().getDeliveryTypeName())
                            .orElseThrow(() -> new ResourceNotFoundException("Delivery type founded by name does not exist!"));
            baseOrder.getOrderDeliveryInfo().setDeliveryType(deliveryType);
        } else {
            throw new ResourceNotFoundException("Delivery type does not present");
        }
    }

    private BaseOrderDTO resolveBuyNowOrderSave(BuyNowOrderDTO buyNowOrderDTO) {
        BuyNowOrder buyNowOrder = buyNowOrderMapper.toEntity(buyNowOrderDTO);
        //
        resolveDeliveryType(buyNowOrder);
        //
        resolveProducts(buyNowOrder.getOrderProducts());
        //
        BuyNowOrder savedOrder = buyNowOrderRepository.save(buyNowOrder);
        //
        resolveOrderFloristInfo(savedOrder);
        //
        return buyNowOrderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional
    public void partialUpdate(OrderPartialUpdate partialUpdate) {
        final Long orderId = partialUpdate.getOrderId();

        if (Objects.nonNull(partialUpdate.getOrderFloristChoice())) {
            this.patchOrderSetChosenFlorist(orderId, partialUpdate.getOrderFloristChoice());
        }
        if (Objects.nonNull(partialUpdate.getOrderFloristCompletion())) {
            this.patchOrderCompletionByFlorist(orderId, partialUpdate.getOrderFloristCompletion());
            this.completeOrder(orderId);
        }
    }

    private void patchOrderSetChosenFlorist(final Long orderId, final OrderFloristChoiceDTO orderFloristChoice) {
        final OrderFloristInfo orderFloristInfo = resolveOrderFloristInfoByOrderId(orderId);

        orderFloristInfo.setFloristId(orderFloristChoice.getFloristId());
    }

    private void patchOrderCompletionByFlorist(final Long orderId, final OrderFloristCompletionDTO orderFloristCompletion) {
        final OrderFloristInfo orderFloristInfo = resolveOrderFloristInfoByOrderId(orderId);
        orderFloristInfo.setFloristComment(orderFloristCompletion.getFloristComment());
    }

    private void completeOrder(final Long orderId) {
        final BaseOrder baseOrder = resolveBaseOrderById(orderId);
        baseOrder.setOrderStatus(OrderStatus.COMPLETED);
    }

    private BaseOrder resolveBaseOrderById(final Long orderId) {
        if (usualOrderRepository.existsById(orderId)) {
            return usualOrderRepository.getOne(orderId);
        } else if (buyNowOrderRepository.existsById(orderId)) {
            return buyNowOrderRepository.getOne(orderId);
        }
        throw new ResourceNotFoundException("Order with id=" + orderId + " does not exist!");
    }

    private OrderFloristInfo resolveOrderFloristInfoByOrderId(final Long orderId) {
        return orderFloristInfoRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Can`t resolve order florist info  with orderId=" + orderId));
    }

}
