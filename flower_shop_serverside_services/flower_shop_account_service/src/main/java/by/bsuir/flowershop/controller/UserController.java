package by.bsuir.flowershop.controller;

import by.bsuir.flowershop.dto.user.UserDTO;
import by.bsuir.flowershop.dto.validation.annotation.PositiveLong;
import by.bsuir.flowershop.security.core.CurrentUser;
import by.bsuir.flowershop.security.core.UserPrincipal;
import by.bsuir.flowershop.security.dto.ApiResponse;
import by.bsuir.flowershop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.bsuir.flowershop.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<?> me(@CurrentUser UserPrincipal userPrincipal) {

        System.out.println("get me works");

        final String userEmail = userPrincipal.getEmail();

        if (userService.existsByEmail(userEmail)) {
            return ResponseEntity.ok(userService.getByEmail(userEmail));
        }

        return ResponseEntity.ok(new ApiResponse(false, "user does not exist!"));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") @PositiveLong String id,
                                           @CurrentUser UserPrincipal userPrincipal,
                                           @RequestBody @Valid UserDTO userDTO,
                                           BindingResult bindingResult) {
        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        return ResponseEntity.ok(userService.update(userDTO, userPrincipal.getId()));

    }

}
