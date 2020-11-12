package by.bsuir.controller;

import by.bsuir.dto.model.basket.AddProductBasketDTO;
import by.bsuir.dto.model.basket.BasketDTO;
import by.bsuir.dto.model.basket.UpdateProductBasketDTO;
import by.bsuir.service.api.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/user/{userId}/basket")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;


    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<BasketDTO> addProduct(@PathVariable("userId") Long userId,
                                                @RequestBody @Valid AddProductBasketDTO addProductBasketDTO,
                                                BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        addProductBasketDTO.setUserId(userId);
        BasketDTO basketDto = basketService.addProduct(addProductBasketDTO);

        return new ResponseEntity<>(
                basketDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping
    public ResponseEntity<BasketDTO> updateProduct(@PathVariable("userId") Long userId,
                                                   @RequestBody @Valid UpdateProductBasketDTO updateProductBasketDTO,
                                                   BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        updateProductBasketDTO.setUserId(userId);
        BasketDTO basketDto = basketService.updateProduct(updateProductBasketDTO);

        return ResponseEntity.ok(basketDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @DeleteMapping
    public ResponseEntity<BasketDTO> deleteProduct(@PathVariable("userId") Long userId,
                                                   @RequestBody @Valid AddProductBasketDTO addProductBasketDTO,
                                                   BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);
        addProductBasketDTO.setUserId(userId);
        BasketDTO basketDto = basketService.deleteProduct(addProductBasketDTO);

        return ResponseEntity.ok(basketDto);
    }



    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping
    public ResponseEntity<BasketDTO> findBasket(@PathVariable("userId") Long userId) {
        BasketDTO basketDto = basketService.findAll(userId);
        return ResponseEntity.ok(basketDto);
    }
}
