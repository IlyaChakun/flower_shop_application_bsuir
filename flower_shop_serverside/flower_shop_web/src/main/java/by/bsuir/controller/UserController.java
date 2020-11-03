package by.bsuir.controller;

import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.service.api.AdminService;
import by.bsuir.service.api.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final ClientService clientService;
    private final AdminService adminService;

    @GetMapping("/me")
    public ResponseEntity<?> me(@CurrentUser UserPrincipal userPrincipal) {


       // return ResponseEntity.ok(userService.findById(userPrincipal.getId()));
        return null;
    }

}
