package by.bsuir.service.api;

import by.bsuir.dto.model.basket.AddUpdateProductBasketDTO;
import by.bsuir.dto.model.basket.BasketDTO;

public interface BasketService {

    BasketDTO addProduct(AddUpdateProductBasketDTO addUpdateProductBasketDTO);

    BasketDTO updateProduct(AddUpdateProductBasketDTO updateProductBasketDTO);

    BasketDTO deleteProduct(AddUpdateProductBasketDTO addUpdateProductBasketDTO);

    BasketDTO findBasket(Long userId);
}
