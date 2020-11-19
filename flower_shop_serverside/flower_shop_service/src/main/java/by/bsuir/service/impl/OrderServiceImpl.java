package by.bsuir.service.impl;

import by.bsuir.dto.mapper.order.OrderMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.entity.cart.Cart;
import by.bsuir.entity.cart.CartItem;
import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderProduct;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.user.Client;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.CartRepository;
import by.bsuir.repository.api.FlowerBouquetRepository;
import by.bsuir.repository.api.FlowerRepository;
import by.bsuir.repository.api.OrderRepository;
import by.bsuir.repository.api.user.ClientRepository;
import by.bsuir.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderMapperDTO orderMapperDTO;
    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    private final FlowerRepository flowerRepository;
    private final FlowerBouquetRepository flowerBouquetRepository;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Client client = resolveClient(orderDTO.getOrderInfo().getClient().getId());
        Cart cart = client.getCart();
        Order newOrder = createOrder(cart.getCartItems(), orderDTO);
        Order savedOrder = orderRepository.save(newOrder);
        clearCart(cart);

        return orderMapperDTO.toDto(savedOrder);
    }


    private Order createOrder(List<CartItem> cartItems, OrderDTO orderDTO) {
        if (cartItems.isEmpty()) {
            logger.error("Order creation with empty cart!");
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "cart_is_empty",
                    "Your cart is empty!");
        }

        Order orderFromUI = orderMapperDTO.toEntity(orderDTO);

        Order newOrder = new Order();
        newOrder.setOrderInfo(orderFromUI.getOrderInfo());
        newOrder.setOrderStatus(OrderStatus.NEW);//TODO на скок понимаю заказ в draft только пока в корзине а тут он уже в роли нового но хз хз
        newOrder.setOrderProducts(resolveProducts(cartItems));
        return newOrder;
    }

    private void clearCart(Cart cart) {
        cart.setCartItems(null);
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    private Set<OrderProduct> resolveProducts(List<CartItem> cartItems) {
        return
                cartItems.stream()
                        .map(cartItem -> {

                            //TODO разбирать на методы железно нихуя не понятно
                            AbstractFlowerProduct product = findAbstractFlowerProductById(cartItem.getProduct().getId());
                            product.setAvailableAmountOnStock(product.getAvailableAmountOnStock() - cartItem.getQuantity());

                            OrderProduct orderProduct = new OrderProduct();
                            orderProduct.setProduct(product);
                            orderProduct.setQuantity(cartItem.getQuantity());

                            return orderProduct;
                        })
                        .collect(Collectors.toSet());
    }


    private AbstractFlowerProduct findAbstractFlowerProductById(Long productId) {

        AbstractFlowerProduct product;
        if (flowerRepository.findById(productId).isPresent()) {
            product = flowerRepository.getOne(productId);
        } else {
            product = flowerBouquetRepository.findById(productId)
                    .orElseThrow(() -> {
                        logger.error("No Flower or Flower Bouquet with id={}", productId);
                        throw new ResourceNotFoundException("No Flower or Flower Bouquet with id=" + productId);
                    });
        }
        return product;
    }


    private Client resolveClient(Long clientId) {
        return
                clientRepository.findById(clientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Client with id=" + clientId + " not found!"));
    }


    @Override
    public OrderDTO findByIdAndClientId(Long orderId, Long clientId) {
        Order order = orderRepository.findByIdAndOrderInfoClientId(orderId, clientId)
                .orElseThrow(() -> {
                            logger.error("No order with id={} for client with id={}", orderId, clientId);
                            return new ResourceNotFoundException("No order with id=" + orderId + " for client with id=" + clientId);
                        }
                );

        return orderMapperDTO.toDto(order);
    }


    @Override
    public PageWrapper<OrderDTO> findAllByClientId(int page, int size, Long clientId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> {
                    logger.error("No client with id={}", clientId);
                    return new ResourceNotFoundException("No client with id=" + clientId);
                });

        Pageable pageable = PageRequest.of(page, size);

        Page<Order> orders = orderRepository.findAllByOrderInfoClient(pageable, client);

        return
                new PageWrapper<>(
                        orderMapperDTO.toDtoList(orders.toList()),
                        orders.getTotalPages(),
                        orders.getTotalElements());

    }


    @Override//TODO охуенно придумал)))) но кстать по логике может и пригодится но хз, типо обновлять за админа заказ мб
    public OrderDTO update(OrderDTO orderDTO) {
        return null;
    }
}
