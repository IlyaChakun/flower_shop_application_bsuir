package by.bsuir.service.impl;

import by.bsuir.dto.mapper.cart.CartMapperDTO;
import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.dto.model.cart.CartItemDTO;
import by.bsuir.entity.cart.Cart;
import by.bsuir.entity.cart.CartItem;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.bouqet.FlowerBouquet;
import by.bsuir.entity.product.common.FlowerLengthCost;
import by.bsuir.entity.product.flower.Flower;
import by.bsuir.entity.user.Client;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.CartRepository;
import by.bsuir.repository.api.core.FlowerBouquetRepository;
import by.bsuir.repository.api.core.FlowerRepository;
import by.bsuir.repository.api.user.ClientRepository;
import by.bsuir.service.api.CartService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final FlowerRepository flowerRepository;
    private final FlowerBouquetRepository flowerBouquetRepository;
    private final CartMapperDTO cartMapperDTO;


    @Override
    @Transactional
    public CartDTO addItem(CartItemDTO cartItemDTO) {
        Client client = findClientById(cartItemDTO.getClientId());
        AbstractFlowerProduct product = findAbstractFlowerProductById(cartItemDTO.getProductId());

        checkAvailableAmountOnStockOrException(product, cartItemDTO.getQuantity());

        CartItem cartItem = new CartItem();
        cartItem.setClient(client);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        Cart cart = client.getCart();
        cart.getCartItems().add(cartItem);

        return buildCart(cart);
    }


    @Override
    @Transactional
    public CartDTO updateItem(CartItemDTO cartItemDTO) {
        Client client = findClientById(cartItemDTO.getClientId());
        AbstractFlowerProduct product = findAbstractFlowerProductById(cartItemDTO.getProductId());

        checkAvailableAmountOnStockOrException(product, cartItemDTO.getQuantity());

        Cart cart = client.getCart();
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(cartItemDTO.getQuantity());
            }
        }

        return buildCart(cart);
    }

    @Override
    @Transactional
    public CartDTO deleteItem(CartItemDTO cartItemDTO) {
        Client client = findClientById(cartItemDTO.getClientId());
        AbstractFlowerProduct product = findAbstractFlowerProductById(cartItemDTO.getProductId());

        Cart cart = client.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(product));

        return buildCart(cart);
    }

    @Override
    @Transactional
    public CartDTO findCart(Long clientId) {
        Client client = findClientById(clientId);
        return buildCart(client.getCart());
    }


    private CartDTO buildCart(Cart cart) {

        BigDecimal totalPrice = calculateTotalPrice(cart.getCartItems());
        cart.setTotalPrice(totalPrice);

        int totalElements = cart.getCartItems().size();

        CartDTO cartDTO = cartMapperDTO.toDto(cart);
        cartDTO.setTotalElements(totalElements);

        cartRepository.save(cart);

        return cartDTO;
    }

    private BigDecimal calculateTotalPrice(List<CartItem> cartItems) {

        double totalSum = 0.0;

        for (CartItem item : cartItems) {
            Double price = getPriceByLength(item, item.getFlowerLengthCostId());
            double total = price * item.getQuantity();
            totalSum += total;
        }

        return BigDecimal.valueOf(totalSum);
    }

    private Double getPriceByLength(CartItem item, Long flowerLengthCostId) {
        double price = 0.0;
        for (FlowerLengthCost itemFlowerLengthCost : item.getProduct().getFlowerLengthCosts()) {
            if (itemFlowerLengthCost.getId() == flowerLengthCostId) {
                price = itemFlowerLengthCost.getPrice();
            }
        }
        return price;
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


    private void checkAvailableAmountOnStockOrException(AbstractFlowerProduct product, Integer orderedAmount) {

        Integer availableOnStock = product.getAvailableAmountOnStock();
        boolean isEnoughToOrder = (availableOnStock - orderedAmount) > 0;

        if (availableOnStock == 0 || !isEnoughToOrder) {
            if (product instanceof Flower) {
                logger.error("You have ordered flower={} with amount={}, but available only={}!" +
                        ((Flower) product).getFlowerType().getFlowerType() +
                        +orderedAmount +
                        availableOnStock);
                throw new ServiceException(HttpStatus.CONFLICT.value(),
                        "flowers_not_enough_on_stock",
                        "You have ordered flower=" + ((Flower) product).getFlowerType().getFlowerType() +
                                " with amount=" + orderedAmount + " ,but available only =" + availableOnStock);


            } else {
                logger.error("You have ordered flower bouquet={} with amount={}, but available only={}!" +
                        ((FlowerBouquet) product).getTitle() +
                        +orderedAmount +
                        availableOnStock);
                throw new ServiceException(HttpStatus.CONFLICT.value(),
                        "flower_bouquets_not_enough_on_stock",
                        "You have ordered flower bouquet=" + ((FlowerBouquet) product).getTitle() +
                                " with amount=" + orderedAmount + " ,but available only =" + availableOnStock);
            }
        }
    }


    private Client findClientById(Long userId) {
        return clientRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user with id=" + userId));
    }
}
