package by.bsuir.service.api;

import by.bsuir.dto.model.cart.CartItemDTO;
import by.bsuir.dto.model.cart.CartDTO;

public interface CartService {

    CartDTO addItem(CartItemDTO cartItemDTO);

    CartDTO updateItem(CartItemDTO updateProductBasketDTO);

    CartDTO deleteItem(CartItemDTO cartItemDTO);

    CartDTO findCart(Long userId);
}
