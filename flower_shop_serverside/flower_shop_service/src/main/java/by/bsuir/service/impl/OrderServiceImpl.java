package by.bsuir.service.impl;

import by.bsuir.dto.mapper.order.OrderMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.entity.basket.Basket;
import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.bouqet.FlowerBouquet;
import by.bsuir.entity.product.flower.Flower;
import by.bsuir.entity.user.Client;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.BasketRepository;
import by.bsuir.repository.api.core.FlowerBouquetRepository;
import by.bsuir.repository.api.core.FlowerRepository;
import by.bsuir.repository.api.core.OrderRepository;
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
    private final BasketRepository basketRepository;
    private final FlowerRepository flowerRepository;
    private final FlowerBouquetRepository flowerBouquetRepository;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Client client = resolveClient(orderDTO.getOrderInfo().getClient().getId());
        Basket basket = client.getBasket();
        Order newOrder = createOrder(basket.getBasketProducts(), orderDTO);
        Order savedOrder = orderRepository.save(newOrder);
        clearBasket(basket);

        return orderMapperDTO.toDto(savedOrder);
    }


    private Order createOrder(List<AbstractFlowerProduct> basketProducts,
                              OrderDTO orderDTO) {
        if (basketProducts.isEmpty()) {
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "basket_is_empty",
                    "Your basket is empty!");
        }

        Order orderFromUI = orderMapperDTO.toEntity(orderDTO);

        Order newOrder = new Order();
        newOrder.setOrderInfo(orderFromUI.getOrderInfo());
        newOrder.setOrderStatus(OrderStatus.DRAFT);
        newOrder.setOrderProducts(resolveProducts(basketProducts));
        return newOrder;
    }

    private void clearBasket(Basket basket) {
        basket.setBasketProducts(null);
        basket.setTotalPrice(BigDecimal.ZERO);
        basketRepository.save(basket);
    }

    private Set<AbstractFlowerProduct> resolveProducts(List<AbstractFlowerProduct> products) {
        return
                products.stream()
                        .map(basketProduct -> {


                            AbstractFlowerProduct product = flowerRepository.findById(basketProduct.getId())
                                    .orElseThrow(() -> new ResourceNotFoundException("No flower with id=" + basketProduct.getId()));

                            if (product instanceof Flower) {
                                if (product.getAvailableAmountOnStock() == 0) {
                                    throw new ServiceException(HttpStatus.CONFLICT.value(),
                                            "flower_not_on_stock",
                                            "Flower with type=" + ((Flower) product).getFlowerType().getFlowerType() + " is unavailable now!");
                                } else {
                                    product.setAvailableAmountOnStock(product.getAvailableAmountOnStock() - 1);
                                }

                            } else {

                                if (product.getAvailableAmountOnStock() == 0) {
                                    throw new ServiceException(HttpStatus.CONFLICT.value(),
                                            "flower_bouquet_not_on_stock",
                                            "Flower Bouquet with name=" + ((FlowerBouquet) product).getTitle() + " is unavailable now!");
                                } else {
                                    product.setAvailableAmountOnStock(product.getAvailableAmountOnStock() - 1);
                                }
                            }

                            return product;
                        })
                        .collect(Collectors.toSet());
    }


    private Client resolveClient(Long userId) {
        return
                clientRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User with id=" + userId + " not found!"));
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

        Page<Order> orders = orderRepository.findAllByClient(pageable, client);

        return
                new PageWrapper<>(
                        orderMapperDTO.toDtoList(orders.toList()),
                        orders.getTotalPages(),
                        orders.getTotalElements());

    }


    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        return null;
    }
}
