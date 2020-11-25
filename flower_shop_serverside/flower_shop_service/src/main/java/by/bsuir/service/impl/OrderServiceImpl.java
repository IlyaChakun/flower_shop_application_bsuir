package by.bsuir.service.impl;

import by.bsuir.dto.mapper.order.OrderMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.dto.model.order.OrderRequestDTO;
import by.bsuir.entity.cart.Cart;
import by.bsuir.entity.cart.CartItem;
import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderProduct;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.user.Client;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.FlowerBouquetRepository;
import by.bsuir.repository.api.FlowerRepository;
import by.bsuir.repository.api.OrderRepository;
import by.bsuir.repository.api.cart.CartRepository;
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

import javax.transaction.Transactional;
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
    @Transactional
    public OrderDTO saveOrder(OrderRequestDTO orderRequest) {

        Client client = resolveClient(orderRequest.getClientId());
        Cart cart = client.getCart();
        Order newOrder = createOrder(cart, orderRequest);
        Order savedOrder = orderRepository.save(newOrder);
        clearCart(cart);

        return orderMapperDTO.toDto(savedOrder);
    }


    private Order createOrder(Cart cart, OrderRequestDTO orderRequest) {
        if (cart.getCartItems().isEmpty()) {
            logger.error("Order creation with empty cart!");
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "cart_is_empty",
                    "Your cart is empty!");
        }

        //Order orderFromUI = orderMapperDTO.toEntity(orderDTO);

        Order newOrder = new Order();

        newOrder.setComment(orderRequest.getComment());
        newOrder.setAddress(orderRequest.getAddress());
        newOrder.setFloorNumber(orderRequest.getFloorNumber());
        newOrder.setEntranceNumber(orderRequest.getEntranceNumber());

        newOrder.setTotalAmount(cart.getTotalPrice());
        newOrder.setClient(resolveClient(orderRequest));


        newOrder.setOrderStatus(OrderStatus.NEW);
        newOrder.setOrderProducts(resolveProducts(cart.getCartItems()));
        return newOrder;
    }


    private Client resolveClient(OrderRequestDTO orderRequest) {
        return clientRepository.getOne(orderRequest.getClientId());
    }

    private void clearCart(Cart cart) {
        cart.setCartItems(null);
        cart.setTotalPrice(0D);
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
                            orderProduct.setFlowerLengthCost(cartItem.getFlowerLengthCost());

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
        Order order = orderRepository.findByIdAndClientId(orderId, clientId)
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

        Page<Order> orders = orderRepository.findAllByClient(pageable, client);

        return
                new PageWrapper<>(
                        orderMapperDTO.toDtoList(orders.toList()),
                        orders.getTotalPages(),
                        orders.getTotalElements());

    }


}
