package by.bsuir.controller;

import by.bsuir.dto.model.basket.AddUpdateProductBasketDTO;
import by.bsuir.dto.model.basket.BasketDTO;
import by.bsuir.service.api.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/user/{clientId}/basket")
//TODO опять же ид чисто для читаемост и и он туту не нужен!!!! и посмтори как я корзину делал на моем гите online- store можешь даже скопировать практически
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;


    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<BasketDTO> addProduct(@PathVariable("clientId") Long clientId,
                                                @RequestBody @Valid AddUpdateProductBasketDTO addUpdateProductBasketDTO,
                                                BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        addUpdateProductBasketDTO.setClientId(clientId);
        BasketDTO basketDto = basketService.addProduct(addUpdateProductBasketDTO);

        return ResponseEntity.ok(basketDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping
    public ResponseEntity<BasketDTO> updateProduct(@PathVariable("clientId") Long clientId,
                                                   @RequestBody @Valid AddUpdateProductBasketDTO addUpdateProductBasketDTO,
                                                   BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
        addUpdateProductBasketDTO.setClientId(clientId);
        BasketDTO basketDto = basketService.updateProduct(addUpdateProductBasketDTO);

        return ResponseEntity.ok(basketDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @DeleteMapping
    public ResponseEntity<BasketDTO> deleteProduct(@PathVariable("clientId") Long clientId,
                                                   @RequestBody @Valid AddUpdateProductBasketDTO addUpdateProductBasketDTO,
                                                   BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);
        addUpdateProductBasketDTO.setClientId(clientId);
        BasketDTO basketDto = basketService.deleteProduct(addUpdateProductBasketDTO);

        return ResponseEntity.ok(basketDto);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping
    public ResponseEntity<BasketDTO> findBasket(@PathVariable("clientId") Long clientId) {
        BasketDTO basketDto = basketService.findBasket(clientId);
        return ResponseEntity.ok(basketDto);
    }
}
