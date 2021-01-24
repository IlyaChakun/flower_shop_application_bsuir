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
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.order.buynow.BuyNowOrder;
import by.bsuir.entity.order.delivery.DeliveryType;
import by.bsuir.entity.order.usual.UsualOrder;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.order.BuyNowOrderRepository;
import by.bsuir.repository.api.order.DeliveryTypeRepository;
import by.bsuir.repository.api.order.OrderFloristInfoRepository;
import by.bsuir.repository.api.order.UsualOrderRepository;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "order_type_mismatch_error", "Order type can`t be resolved");
        }
    }

    private BaseOrderDTO resolveUsualOrderSave(UsualOrderDTO usualOrderDTO) {
        UsualOrder usualOrder = usualOrderMapper.toEntity(usualOrderDTO);
        //
        resolveDeliveryType(usualOrder);
        //
        UsualOrder savedOrder = usualOrderRepository.save(usualOrder);
        //
        resolveOrderFloristInfo(savedOrder);
        //
        return usualOrderMapper.toDto(savedOrder);
    }

    private void resolveOrderFloristInfo(BaseOrder baseOrder) {
        OrderFloristInfo orderFloristInfo = new OrderFloristInfo();
        orderFloristInfo.setOrderId(baseOrder.getId());
        baseOrder.setOrderFloristInfo(orderFloristInfo);
    }

    private void resolveDeliveryType(BaseOrder baseOrder) {
        DeliveryType deliveryType = deliveryTypeRepository.findById(baseOrder.getOrderDeliveryInfo().getDeliveryType().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery type not found!"));
        baseOrder.getOrderDeliveryInfo().setDeliveryType(deliveryType);
    }

    private BaseOrderDTO resolveBuyNowOrderSave(BuyNowOrderDTO buyNowOrderDTO) {
        BuyNowOrder buyNowOrder = buyNowOrderMapper.toEntity(buyNowOrderDTO);
        //
        resolveDeliveryType(buyNowOrder);
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
