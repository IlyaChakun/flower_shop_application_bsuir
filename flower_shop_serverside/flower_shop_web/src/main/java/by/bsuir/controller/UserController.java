package by.bsuir.controller;

import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.security.dto.AuthTokenResponse;
import by.bsuir.service.api.ClientService;
import by.bsuir.service.api.ShopAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final ClientService clientService;
    private final ShopAdminService shopAdminService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<?> me(@CurrentUser UserPrincipal userPrincipal) {
        final String userEmail = userPrincipal.getEmail();

        if (clientService.existsByEmail(userEmail)) {
            return ResponseEntity.ok(clientService.getByEmail(userEmail));
        }

        if (shopAdminService.existsByEmail(userEmail)) {
            return ResponseEntity.ok(shopAdminService.getByEmail(userEmail));
        }

        //courier

        return ResponseEntity.ok(new ApiResponse(false, "user does not exist!"));
    }


    @PostMapping("/{id}")
    public ResponseEntity<AbstractUserDTO> updateProfile(@PathVariable("id") @PositiveLong String id,
                                                         @CurrentUser UserPrincipal userPrincipal,
                                                         @Valid AbstractUserDTO userDTO,
                                                         BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final String userEmail = userPrincipal.getEmail();

        AbstractUserDTO user = null;

        if (clientService.existsByEmail(userEmail)) {
            user = clientService.update((ClientDTO) userDTO, id);
        }

//        if (shopAdminService.existsByEmail(userEmail)) {
//            user = shopAdminService.update((ShopAdminDTO) userDTO);
//        }

        return ResponseEntity.ok(user);
    }

}
