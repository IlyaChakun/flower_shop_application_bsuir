package by.bsuir.controller;

import by.bsuir.dto.model.cart.DeleteCartItemDTO;
import by.bsuir.dto.model.cart.RequestCartItemDTO;
import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.service.api.CartService;
import by.bsuir.service.api.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;
    private final ClientService clientService;


    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<CartDTO> addProduct(@RequestBody @Valid RequestCartItemDTO requestCartItemDTO,
                                              @CurrentUser UserPrincipal userPrincipal,
                                              BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        requestCartItemDTO.setClientId(getClientDTO(userPrincipal).getId());
        CartDTO cartDto = cartService.addItem(requestCartItemDTO);

        return ResponseEntity.ok(cartDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @PutMapping
    public ResponseEntity<CartDTO> updateProduct(@RequestBody @Valid RequestCartItemDTO requestCartItemDTO,
                                                 @CurrentUser UserPrincipal userPrincipal,
                                                 BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        requestCartItemDTO.setClientId(getClientDTO(userPrincipal).getId());
        CartDTO cartDto = cartService.updateItem(requestCartItemDTO);

        return ResponseEntity.ok(cartDto);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @DeleteMapping
    public ResponseEntity<CartDTO> deleteProduct(@RequestBody @Valid DeleteCartItemDTO deleteCartItem,
                                                 @CurrentUser UserPrincipal userPrincipal,
                                                 BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        deleteCartItem.setClientId(getClientDTO(userPrincipal).getId());
        CartDTO cartDto = cartService.deleteItem(deleteCartItem);

        return ResponseEntity.ok(cartDto);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT')")
    @GetMapping
    public ResponseEntity<CartDTO> findBasket(@CurrentUser UserPrincipal userPrincipal) {
        CartDTO cartDto = cartService.findCart(getClientDTO(userPrincipal).getId());
        return ResponseEntity.ok(cartDto);
    }


    private ClientDTO getClientDTO(@CurrentUser UserPrincipal userPrincipal) {
        final String userEmail = userPrincipal.getEmail();
        return clientService.findByEmail(userEmail);
    }
    //TODO можно вынести в отдельный контроллер как стат метод
}
