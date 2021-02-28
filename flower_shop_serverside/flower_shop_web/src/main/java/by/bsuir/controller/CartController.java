package by.bsuir.controller;

import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.dto.model.cart.DeleteCartItemDTO;
import by.bsuir.dto.model.cart.RequestCartItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public interface CartController {

    @PostMapping
    ResponseEntity<CartDTO> addItem(@RequestBody @Valid RequestCartItemDTO requestCartItemDTO,
                                    BindingResult result);

    @PutMapping
    ResponseEntity<CartDTO> updateItem(@RequestBody @Valid RequestCartItemDTO updateProductBasketDTO,
                       BindingResult result);

    @DeleteMapping()
    ResponseEntity<CartDTO> deleteItem(@RequestBody @Valid DeleteCartItemDTO deleteCartItem,
                                       BindingResult result);

    @GetMapping()
    ResponseEntity<CartDTO> findCartByUserId(@RequestParam(name = "userId") Long userId);

}
