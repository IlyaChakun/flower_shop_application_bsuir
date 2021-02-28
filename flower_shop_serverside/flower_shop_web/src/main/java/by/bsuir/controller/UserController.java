package by.bsuir.controller;


import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/users")
@CrossOrigin(origins = "*")
public interface UserController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    ResponseEntity<?> me(@CurrentUser UserPrincipal userPrincipal);

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    ResponseEntity<?> updateProfile(@PathVariable("id") @PositiveLong String id,
                                    @CurrentUser UserPrincipal userPrincipal,
                                    @RequestBody @Valid UserDTO userDTO,
                                    BindingResult bindingResult);



}
