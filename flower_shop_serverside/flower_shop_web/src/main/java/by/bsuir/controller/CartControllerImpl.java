package by.bsuir.controller;

import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.dto.model.cart.DeleteCartItemDTO;
import by.bsuir.dto.model.cart.RequestCartItemDTO;
import by.bsuir.service.api.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@AllArgsConstructor
public class CartControllerImpl implements CartController {

    private final CartService cartService;


    @PostMapping
    public ResponseEntity<CartDTO> addItem(@RequestBody @Valid RequestCartItemDTO requestCartItemDTO,
                                           BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CartDTO cartDto = cartService.addItem(requestCartItemDTO);

        return ResponseEntity.ok(cartDto);
    }


    @PutMapping
    public ResponseEntity<CartDTO> updateItem(@RequestBody @Valid RequestCartItemDTO requestCartItemDTO,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        CartDTO cartDto = cartService.updateItem(requestCartItemDTO);

        return ResponseEntity.ok(cartDto);
    }


    @DeleteMapping
    public ResponseEntity<CartDTO> deleteItem(@RequestBody @Valid DeleteCartItemDTO deleteCartItem,
                                              BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        CartDTO cartDto = cartService.deleteItem(deleteCartItem);

        return ResponseEntity.ok(cartDto);
    }

    @Override
    public ResponseEntity<CartDTO> findCartByUserId(@RequestParam(name = "userId") Long userId) {
        CartDTO cartDto = cartService.findCart(userId);
        return ResponseEntity.ok(cartDto);
    }


}
