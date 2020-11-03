package by.bsuir.controller;

import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.service.api.ClientService;
import by.bsuir.service.api.ShopAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final ClientService clientService;
    private final ShopAdminService shopAdminService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<?> me(@CurrentUser UserPrincipal userPrincipal) {

        System.out.println("get me works");

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

}
