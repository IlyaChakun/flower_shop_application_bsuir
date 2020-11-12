package by.bsuir.service.api;

import by.bsuir.dto.model.basket.AddProductBasketDTO;
import by.bsuir.dto.model.basket.BasketDTO;
import by.bsuir.dto.model.basket.UpdateProductBasketDTO;

public interface BasketService {

    BasketDTO addProduct(AddProductBasketDTO addProductBasketDTO);

    BasketDTO updateProduct(UpdateProductBasketDTO updateProductBasketDTO);

    BasketDTO deleteProduct(AddProductBasketDTO addProductBasketDTO);

    BasketDTO findAll(Long userId);
}
