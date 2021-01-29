package by.bsuir.service.impl;

import by.bsuir.dto.mapper.cart.CartMapperDTO;
import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.dto.model.cart.DeleteCartItemDTO;
import by.bsuir.dto.model.cart.RequestCartItemDTO;
import by.bsuir.entity.cart.Cart;
import by.bsuir.entity.cart.CartItem;

import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;

import by.bsuir.repository.api.cart.CartRepository;
import by.bsuir.service.api.CartService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;
    private final CartMapperDTO cartMapperDTO;

    @Override
    @Transactional
    public CartDTO addItem(RequestCartItemDTO requestCartItemDTO) {
//        Client client = findClientById(requestCartItemDTO.getClientId());
//        AbstractFlowerProduct product = findAbstractFlowerProductById(requestCartItemDTO.getProductId());

//        checkAvailableAmountOnStockOrException(product, requestCartItemDTO.getQuantity());
//
//        CartItem cartItem = new CartItem();
//        cartItem.setClientId(requestCartItemDTO.getClientId());
//        cartItem.setProductId(requestCartItemDTO.getProductId());
//
//        cartItem.setProductLengthCostId(requestCartItemDTO.getProductLengthCostId());
//        cartItem.setQuantity(requestCartItemDTO.getQuantity());
//
//        Cart cart = client.getCart();
//        cart.getCartItems().add((cartItem));
//
//        return buildCart(cart);
        return null;
    }

    private void resolveLengthCost(Long id) {
//         flowerLengthCostRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Flower length cost with id=" + id + " not found"));
    }

    @Override
    @Transactional
    public CartDTO updateItem(RequestCartItemDTO requestCartItemDTO) {
//        Client client = findClientById(requestCartItemDTO.getClientId());
//        AbstractFlowerProduct product = findAbstractFlowerProductById(requestCartItemDTO.getProductId());
//
//        checkAvailableAmountOnStockOrException(product, requestCartItemDTO.getQuantity());
//
//        Cart cart = client.getCart();
//        List<CartItem> cartItems = cart.getCartItems();
//
//        for (CartItem item : cartItems) {
//            if (item.getProduct().equals(product)) {
//                item.setQuantity(requestCartItemDTO.getQuantity());
//                item.setFlowerLengthCost(resolveLengthCost(requestCartItemDTO.getProductLengthCostId()));
//            }
//        }
//
//        return buildCart(cart);
        return null;
    }

    @Override
    @Transactional
    public CartDTO deleteItem(DeleteCartItemDTO deleteCartItemDTO) {
//        Client client = findClientById(deleteCartItemDTO.getClientId());
//        AbstractFlowerProduct product = findAbstractFlowerProductById(deleteCartItemDTO.getProductId());
//
//        Cart cart = client.getCart();
//        List<CartItem> cartItems = cart.getCartItems();
//        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(product));
//
//        return buildCart(cart);
        return null;
    }

    @Override
    @Transactional
    public CartDTO findCart(Long clientId) {
//        Client client = findClientById(clientId);
//        return buildCart(client.getCart());
        return null;
    }


//    private CartDTO buildCart(Cart cart) {
//
//        Double totalPrice = calculateTotalPrice(cart.getCartItems());
//        cart.setTotalPrice(totalPrice);
//
//        int totalElements = cart.getCartItems().size();
//
//        CartDTO cartDTO = cartMapperDTO.toDto(cart);
//        cartDTO.setTotalElements(totalElements);
//        cartDTO.setTotalPrice(cart.getTotalPrice());
//
//
//        cartRepository.save(cart);
//
//        return cartDTO;
//    }
//
//    private Double calculateTotalPrice(List<CartItem> cartItems) {
//
//        double totalSum = 0.0;
//
//        for (CartItem item : cartItems) {
//            Double price = item.getFlowerLengthCost().getPrice();
//            double total = price * item.getQuantity();
//            totalSum += total;
//        }
//
//        return totalSum;
//    }
//
//
//    private AbstractFlowerProduct findAbstractFlowerProductById(Long productId) {
//
//        AbstractFlowerProduct product;
//        if (flowerRepository.findById(productId).isPresent()) {
//            product = flowerRepository.getOne(productId);
//        } else {
//            product = flowerBouquetRepository.findById(productId)
//                    .orElseThrow(() -> {
//                        logger.error("No Flower or Flower Bouquet with id={}", productId);
//                        throw new ResourceNotFoundException("No Flower or Flower Bouquet with id=" + productId);
//                    });
//        }
//        return product;
//    }
//
//
//    private void checkAvailableAmountOnStockOrException(AbstractFlowerProduct product, Integer orderedAmount) {
//
//        Integer availableOnStock = product.getAvailableAmountOnStock();
//        boolean isEnoughToOrder = (availableOnStock - orderedAmount) >= 0;
//
//        if (availableOnStock == 0 || !isEnoughToOrder) {
//            if (product instanceof Flower) {
//                logger.error("You have ordered flower={} with amount={}, but available only={}!" +
//                        ((Flower) product).getFlowerType().getFlowerType() +
//                        +orderedAmount +
//                        availableOnStock);
//                throw new ServiceException(HttpStatus.CONFLICT.value(),
//                        "flowers_not_enough_on_stock",
//                        "You have ordered flower=" + ((Flower) product).getFlowerType().getFlowerType() +
//                                " with amount=" + orderedAmount + " ,but available only =" + availableOnStock);
//
//
//            } else {
//                logger.error("You have ordered flower bouquet={} with amount={}, but available only={}!" +
//                        ((FlowerBouquet) product).getTitle() +
//                        +orderedAmount +
//                        availableOnStock);
//                throw new ServiceException(HttpStatus.CONFLICT.value(),
//                        "flower_bouquets_not_enough_on_stock",
//                        "You have ordered flower bouquet=" + ((FlowerBouquet) product).getTitle() +
//                                " with amount=" + orderedAmount + " ,but available only =" + availableOnStock);
//            }
//        }
//    }
//
//
//    private Client findClientById(Long userId) {
//        return clientRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("No user with id=" + userId));
//    }
}
