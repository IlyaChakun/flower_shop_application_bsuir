package by.bsuir.service.api;

import by.bsuir.dto.model.cart.DeleteCartItemDTO;
import by.bsuir.dto.model.cart.RequestCartItemDTO;
import by.bsuir.dto.model.cart.CartDTO;

public interface CartService {

    CartDTO addItem(RequestCartItemDTO requestCartItemDTO);

    CartDTO updateItem(RequestCartItemDTO updateProductBasketDTO);

    CartDTO deleteItem(DeleteCartItemDTO deleteCartItemDTO);

    CartDTO findCart(Long userId);

}
