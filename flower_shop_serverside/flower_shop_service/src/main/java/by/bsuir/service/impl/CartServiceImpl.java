package by.bsuir.service.impl;

import by.bsuir.dto.mapper.cart.CartMapperDTO;
import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.dto.model.cart.DeleteCartItemDTO;
import by.bsuir.dto.model.cart.RequestCartItemDTO;
import by.bsuir.entity.cart.Cart;
import by.bsuir.entity.cart.CartItem;
import by.bsuir.entity.product.Product;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.cart.CartItemRepository;
import by.bsuir.repository.api.cart.CartRepository;
import by.bsuir.repository.api.product.ProductLengthCostRepository;
import by.bsuir.repository.api.product.ProductRepository;
import by.bsuir.repository.api.user.UserRepository;
import by.bsuir.service.api.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapperDTO cartMapperDTO;


    private final ProductLengthCostRepository productLengthCostRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CartDTO addItem(RequestCartItemDTO requestCartItemDTO) {
        log.info("in addItem method");
        log.info("clientId={}, productId={}, productLengthCostId={}, quantity={}",
                requestCartItemDTO.getClientId(),
                requestCartItemDTO.getProductId(),
                requestCartItemDTO.getProductLengthCostId(),
                requestCartItemDTO.getQuantity());

        Product product = resolveProductOrThrowException(requestCartItemDTO.getProductId());
        Long clientId = requestCartItemDTO.getClientId();

        createCartIfNotExist(clientId);

        checkAvailableAmountOnStockOrException(product, requestCartItemDTO.getQuantity());

        Cart cart = resolveByClientId(clientId);

        //        cart.getCartItems()
        //                .forEach(cartItem -> {
        //                    if (cartItem.getProductId().equals(requestCartItemDTO.getProductId())
        //                            && cartItem.getProductLengthCostId().equals(requestCartItemDTO.getProductLengthCostId())) {
        //                        Integer quantity = cartItem.getQuantity() + requestCartItemDTO.getQuantity();
        //                        cartItem.setQuantity(quantity);
        //
        //                    }
        //                });

        Optional<CartItem> cartItemOptional =
                cartItemRepository.findByClientIdAndProductIdAndProductLengthCostId(
                        requestCartItemDTO.getClientId(),
                        requestCartItemDTO.getProductId(),
                        requestCartItemDTO.getProductLengthCostId()
                );

        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            Integer quantity = cartItem.getQuantity() + requestCartItemDTO.getQuantity();
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setClientId(requestCartItemDTO.getClientId());
            cartItem.setProductId(requestCartItemDTO.getProductId());

            cartItem.setProductLengthCostId(requestCartItemDTO.getProductLengthCostId());
            cartItem.setQuantity(requestCartItemDTO.getQuantity());

            cart.getCartItems().add((cartItem));
        }
        return buildCart(cart);
    }

    private void createCartIfNotExist(Long clientId) {
        if (!cartRepository.existsByClientId(clientId)) {
            Cart cart = new Cart();
            cart.setClientId(clientId);
            cartRepository.save(cart);
        }
    }

    private void resolveLengthCostOrThrowException(Long id) {
        log.info("in resolveLengthCostOrThrowException method");
        log.info("id={}", id);
        productLengthCostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flower length cost with id=" + id + " not found"));
    }

    @Override
    @Transactional
    public CartDTO updateItem(RequestCartItemDTO requestCartItemDTO) {
        log.info("in updateItem method");
        log.info("clientId={}, productId={}, productLengthCostId={}, quantity={}",
                requestCartItemDTO.getClientId(),
                requestCartItemDTO.getProductId(),
                requestCartItemDTO.getProductLengthCostId(),
                requestCartItemDTO.getQuantity());

        Product product = resolveProductOrThrowException(requestCartItemDTO.getProductId());
        Long clientId = requestCartItemDTO.getClientId();
        checkAvailableAmountOnStockOrException(product, requestCartItemDTO.getQuantity());

        Cart cart = resolveByClientId(clientId);
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems) {
            Product inCartProduct = productRepository.getOne(item.getProductId());
            if (inCartProduct.equals(product)) {
                item.setQuantity(requestCartItemDTO.getQuantity());
                Long productLengthCostId = requestCartItemDTO.getProductLengthCostId();
                resolveLengthCostOrThrowException(productLengthCostId);
                item.setProductLengthCostId(productLengthCostId);
            }
        }

        return buildCart(cart);
    }

    @Override
    @Transactional
    public CartDTO deleteItem(DeleteCartItemDTO deleteCartItemDTO) {

        log.info("in deleteItem method");
        log.info("clientId={}, productId={}",
                deleteCartItemDTO.getClientId(),
                deleteCartItemDTO.getProductId());

        Product product = resolveProductOrThrowException(deleteCartItemDTO.getProductId());
        Long clientId = deleteCartItemDTO.getClientId();

        Cart cart = resolveByClientId(clientId);

        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(
                cartItem -> {
                    //                    Product inCartProduct = productRepository.getOne(cartItem.getProductId());
                    //                    return inCartProduct.equals(product);
                    return (cartItem.getProductId()
                            .equals(deleteCartItemDTO.getProductId())
                            &&
                            cartItem.getProductLengthCostId()
                                    .equals(deleteCartItemDTO.getProductLengthCostId()));
                }
        );

        return buildCart(cart);
    }

    @Override
    @Transactional
    public CartDTO findCart(Long clientId) {
        log.info("in findCart method");
        Cart cart = resolveByClientId(clientId);
        return buildCart(cart);
    }


    private CartDTO buildCart(Cart cart) {

        log.info("in buildCart method");

    Integer discount  = null;

        if(userRepository.existsById(cart.getClientId())) {
            discount = userRepository.getUserDiscountById(cart.getClientId());
        }
        Double totalPrice = calculateTotalPrice(cart.getCartItems(),discount );
        cart.setTotalPrice(totalPrice);

        int totalElements = cart.getCartItems().size();

        CartDTO cartDTO = cartMapperDTO.toDto(cart);
        cartDTO.setTotalElements(totalElements);
        cartDTO.setTotalPrice(cart.getTotalPrice());


        cartRepository.save(cart);

        log.info("cartDTO={}", cartDTO);

        return cartDTO;
    }

    private Double calculateTotalPrice(List<CartItem> cartItems, Integer userDiscount) {
        log.info("in calculateTotalPrice method");

        double totalSum = 0.0;

        for (CartItem item : cartItems) {
            Long productLengthCostId = item.getProductLengthCostId();
            Double price = productLengthCostRepository.getOne(productLengthCostId).getCost();
            double total = price * item.getQuantity();
            totalSum += total;
        }

        log.info("totalSum={}", totalSum);
        return Objects.isNull(userDiscount)? totalSum : totalSum - (totalSum*userDiscount /100);
    }


    private Product resolveProductOrThrowException(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("No Product with id={}", productId);
                    throw new ResourceNotFoundException("No Product with id=" + productId);
                });
    }


    private void checkAvailableAmountOnStockOrException(Product product, Integer orderedAmount) {

        Integer availableOnStock = product.getAvailableAmount();
        boolean isEnoughToOrder = (availableOnStock - orderedAmount) >= 0;

        if (availableOnStock == 0 || !isEnoughToOrder) {

            log.error("You have ordered categoryId={} with amount={}, but available only={}!",
                    product.getCategoryId(),
                    orderedAmount,
                    availableOnStock);

            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "flowers_not_enough_on_stock",
                    "You have ordered product with categoryId=" + product.getCategoryId() +
                            " with amount=" + orderedAmount + " ,but available only =" + availableOnStock);

        }
    }

    private Cart resolveByClientId(final Long clientId) {
        log.info("in resolveByClientId method");
        log.info("clientId={}", clientId);
        if (!cartRepository.existsByClientId(clientId)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "empty_cart_error", "Your cart is empty!");
        }
        return cartRepository.getByClientId(clientId);
    }


}
