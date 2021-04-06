package by.bsuir.service.impl;

import by.bsuir.dto.mapper.order.OrderMapperDTO;
import by.bsuir.dto.mapper.order.OrderReviewMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.dto.model.order.criteria.UsualOrderSearchCriteriaDTO;
import by.bsuir.dto.model.order.partial.OrderCloseDTO;
import by.bsuir.dto.model.order.partial.OrderFloristChoiceDTO;
import by.bsuir.dto.model.order.partial.OrderFloristCompletionDTO;
import by.bsuir.dto.model.order.partial.OrderPartialUpdate;
import by.bsuir.dto.model.order.review.OrderReviewDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.dto.model.user.signup.UserSignUpRequest;
import by.bsuir.entity.florist.Florist;
import by.bsuir.entity.florist.FloristStatistic;
import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderFloristInfo;
import by.bsuir.entity.order.OrderProduct;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.order.delivery.DeliveryType;
import by.bsuir.entity.product.Product;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.cart.CartRepository;
import by.bsuir.repository.api.florist.FloristRepository;
import by.bsuir.repository.api.order.DeliveryTypeRepository;
import by.bsuir.repository.api.order.OrderFloristInfoRepository;
import by.bsuir.repository.api.order.OrderRepository;
import by.bsuir.repository.api.order.OrderReviewRepository;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.security.service.api.UserSecurityService;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderMapperDTO orderMapper;
    private final OrderRepository orderRepository;


    private final DeliveryTypeRepository deliveryTypeRepository;
    private final OrderFloristInfoRepository orderFloristInfoRepository;

    private final OrderReviewRepository orderReviewRepository;
    private final OrderReviewMapperDTO orderReviewMapper;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final FloristRepository floristRepository;

    private final CommonServiceHelper commonServiceHelper;

    private final UserSecurityService accountClient;

    @Override
    public PageWrapper<OrderDTO> findAll(int page, int size, UsualOrderSearchCriteriaDTO searchParams) {
        Pageable pageable = commonServiceHelper.getPageable(page, size);

        //TODO сделать спецификацию 1. ид клиента (если есть) 2. ордер статус( есть всегда  с него можно начинать)


        Page<Order> usualOrders;
        if (Objects.nonNull(searchParams.getClientId())) {
            usualOrders =
                    orderRepository.findAllByClientIdAndOrderStatus(
                            pageable,
                            searchParams.getClientId(),
                            searchParams.getOrderStatus());
        } else {
            usualOrders =
                    orderRepository.findAllByOrderStatus(
                            pageable,
                            searchParams.getOrderStatus());
        }


        return
                new PageWrapper<>(
                        orderMapper.toDtoList(usualOrders.toList()),
                        usualOrders.getTotalPages(),
                        usualOrders.getTotalElements());
    }


    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id=" + id + " does not exist!"));

        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public OrderDTO save(OrderDTO orderDTO) {

        Order order;
        boolean deleteCart = true;
        if (orderDTO instanceof BuyNowOrderDTO) {
            deleteCart = false;
            order = this.resolveBuyNowOrderToOrder((BuyNowOrderDTO) orderDTO);
        } else {
            order = orderMapper.toEntity(orderDTO);
        }

        return this.resolveOrderSave(order, deleteCart);
    }

    private void deleteCart(Long clientId) {
        if (cartRepository.existsByClientId(clientId)) {
            cartRepository.deleteByClientId(clientId);
        } else {
            throw new ResourceNotFoundException("Cart with clientId=" + clientId + " does not found!");
        }
    }

    private OrderDTO resolveOrderSave(Order order, boolean deleteCart) {
        //
        resolveDeliveryType(order);
        //
        resolveProducts(order.getOrderProducts());
        //
        Order savedOrder = orderRepository.save(order);
        //
        resolveOrderFloristInfo(savedOrder);
        //
        if (deleteCart) {//если обычный заказ то будет что удалять
            deleteCart(order.getClientId());
        }
        //
        return orderMapper.toDto(savedOrder);
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

    private void resolveOrderFloristInfo(Order order) {
        OrderFloristInfo orderFloristInfo = new OrderFloristInfo();
        orderFloristInfo.setOrderId(order.getId());
        order.setOrderFloristInfo(orderFloristInfo);
    }

    private void resolveDeliveryType(Order order) {
        if (Objects.nonNull(order.getOrderDeliveryInfo().getDeliveryType().getId())) {
            DeliveryType deliveryType = deliveryTypeRepository.findById(order.getOrderDeliveryInfo().getDeliveryType().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Delivery type founded by id does not exist!"));
            order.getOrderDeliveryInfo().setDeliveryType(deliveryType);
        } else if (Objects.nonNull(order.getOrderDeliveryInfo().getDeliveryType().getDeliveryTypeName())) {
            DeliveryType deliveryType =
                    deliveryTypeRepository.findByDeliveryTypeName(order.getOrderDeliveryInfo().getDeliveryType().getDeliveryTypeName())
                            .orElseThrow(() -> new ResourceNotFoundException("Delivery type founded by name does not exist!"));
            order.getOrderDeliveryInfo().setDeliveryType(deliveryType);
        } else {
            throw new ResourceNotFoundException("Delivery type does not present");
        }
    }

    private Order resolveBuyNowOrderToOrder(BuyNowOrderDTO buyNowOrderDTO) {

        UserDTO userDTO;
        if (accountClient.existsByEmail(buyNowOrderDTO.getEmail())) {
            userDTO = accountClient.getOneByMail(buyNowOrderDTO.getEmail());
        } else {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
            userSignUpRequest.setRoleType("ROLE_CLIENT");
            userSignUpRequest.setEmail(buyNowOrderDTO.getEmail());
            userSignUpRequest.setName(buyNowOrderDTO.getName());
            userSignUpRequest.setPhoneNumber(buyNowOrderDTO.getPhoneNumber());
            userSignUpRequest.setPassword(java.util.UUID.randomUUID().toString());

            //TODO сгенерировать пароль и отправить его потом на почту

            userDTO = accountClient.registerUser(userSignUpRequest);
        }

        Order order = orderMapper.toEntity((OrderDTO) buyNowOrderDTO);
        order.setClientId(userDTO.getId());

        return order;
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

        if (Objects.nonNull(partialUpdate.getOrderReview())) {
            patchUserOrderRating(orderId, partialUpdate.getOrderReview());
        }

        if (Objects.nonNull(partialUpdate.getOrderClose())) {
            this.closeOrder(orderId, partialUpdate.getOrderClose());
        }

        if (Objects.nonNull(partialUpdate.getOrderAutoFloristChoose())) {
            this.autoFloristChoose(orderId);
        }

    }

    private void autoFloristChoose(Long orderId) {
        final Order order = resolveOrderById(orderId);

        Optional<Florist> floristOptional = floristRepository.findByActiveOrdersCountBetweenAndFloristStatisticFloristRatingBetween(0, 2, 4D, 5D);

        if (floristOptional.isPresent()) {
            final OrderFloristInfo orderFloristInfo = resolveOrderFloristInfoByOrderId(orderId);
            Florist florist = floristOptional.get();
            decreaseActiveOrdersCount(florist, florist.getActiveOrdersCount() + 1);
            orderFloristInfo.setFloristId(florist.getId());
            order.setOrderStatus(OrderStatus.IN_PROCESS);
        } else {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "no_florist_error", " Can`t find available florist, please wait!");
        }
    }

    private void closeOrder(Long orderId, OrderCloseDTO orderCloseDTO) {
        final Order order = resolveOrderById(orderId);
        order.setOrderStatus(OrderStatus.CLOSED);
        order.setCloseDescription(orderCloseDTO.getDescription());

        if(Objects.nonNull(order.getOrderFloristInfo().getFloristId())) {
            final Long floristId = order.getOrderFloristInfo().getFloristId();
            Florist florist = floristRepository.getOne(floristId);

            decreaseActiveOrdersCount(florist, florist.getActiveOrdersCount() - 1);
        }

    }

    private void decreaseActiveOrdersCount(Florist florist, int i) {
        florist.setActiveOrdersCount(i);
    }

    private void patchUserOrderRating(Long orderId, OrderReviewDTO orderReviewDTO) {
        final Order order = resolveOrderById(orderId);
        order.setOrderReview(orderReviewMapper.toEntity(orderReviewDTO));

        updateFloristRating(orderReviewDTO, order);
    }

    private void updateFloristRating(OrderReviewDTO orderReviewDTO, Order order) {
        FloristStatistic floristStatistic = getFloristStatistic(order);

        //calculate new rating sum
        final double initialFloristRatingSum = Objects.isNull(floristStatistic.getFloristRatingSum()) ? 0 : floristStatistic.getFloristRatingSum();
        final double floristRatingSum = initialFloristRatingSum + orderReviewDTO.getRating();
        floristStatistic.setFloristRatingSum(floristRatingSum);
    }

    private void patchOrderSetChosenFlorist(final Long orderId, final OrderFloristChoiceDTO orderFloristChoice) {
        final OrderFloristInfo orderFloristInfo = resolveOrderFloristInfoByOrderId(orderId);

        final Long floristId = orderFloristChoice.getFloristId();
        Florist florist = floristRepository.getOne(floristId);

        if (florist.getActiveOrdersCount() >= 3) {
            throw new ServiceException(HttpStatus.CONFLICT.value(), "florist_not_available_error", "Florist can`t have more than 3 active orders");
        } else {
            decreaseActiveOrdersCount(florist, florist.getActiveOrdersCount() + 1);
        }

        orderFloristInfo.setFloristId(floristId);

        Order order = resolveOrderById(orderId);
        order.setOrderStatus(OrderStatus.IN_PROCESS);

    }

    private void patchOrderCompletionByFlorist(final Long orderId, final OrderFloristCompletionDTO orderFloristCompletion) {
        final OrderFloristInfo orderFloristInfo = resolveOrderFloristInfoByOrderId(orderId);
        orderFloristInfo.setFloristComment(orderFloristCompletion.getFloristComment());

        final Order order = resolveOrderById(orderId);

        FloristStatistic floristStatistic = getFloristStatistic(order);

        //calculate new orders count
        final int initialCompletedOrderCount = Objects.isNull(floristStatistic.getCompletedOrdersCount()) ? 0 : floristStatistic.getCompletedOrdersCount();
        final int floristCompletedOrdersCount = initialCompletedOrderCount + 1;
        floristStatistic.setCompletedOrdersCount(floristCompletedOrdersCount);

        ///SET FLORIST FREE
        Florist florist = floristRepository.getOne(order.getOrderFloristInfo().getFloristId());
        decreaseActiveOrdersCount(florist, florist.getActiveOrdersCount() - 1);

    }

    private FloristStatistic getFloristStatistic(Order order) {
        Florist florist = floristRepository.getOne(order.getOrderFloristInfo().getFloristId());
        return florist.getFloristStatistic();
    }

    private void completeOrder(final Long orderId) {
        final Order order = resolveOrderById(orderId);
        order.setOrderStatus(OrderStatus.COMPLETED);
    }

    private Order resolveOrderById(final Long orderId) {
        if (orderRepository.existsById(orderId)) {
            return orderRepository.getOne(orderId);
        }
        throw new ResourceNotFoundException("Order with id=" + orderId + " does not exist!");
    }

    private OrderFloristInfo resolveOrderFloristInfoByOrderId(final Long orderId) {
        return orderFloristInfoRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Can`t resolve order florist info  with orderId=" + orderId));
    }

}
