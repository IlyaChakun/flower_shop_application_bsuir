package by.bsuir.controller;

import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.exception.ControllerException;
import by.bsuir.security.core.TokenProvider;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.security.dto.AuthTokenResponse;
import by.bsuir.security.dto.LoginRequest;
import by.bsuir.security.dto.UserIdentityAvailability;
import by.bsuir.security.dto.signup.ClientSignUpRequest;
import by.bsuir.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserSecurityService userService;
    private final TokenProvider tokenProvider;


    @PostMapping("/login")
    public ResponseEntity<AuthTokenResponse> authenticateUser(@RequestBody @Valid LoginRequest loginRequest,
                                                              BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final AuthTokenResponse authTokenResponse = tokenProvider.buildAuthTokenResponse(authentication);

        return ResponseEntity.ok(authTokenResponse);
    }

    @PostMapping("/client/sign-up")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid ClientSignUpRequest signUpRequest,
                                                BindingResult result) {
        checkBindingResultAndThrowExceptionIfInvalid(result);

        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmedPassword())) {
            throw new ControllerException("The password you entered did not match the confirmed password!");
        }

        AbstractUserDTO addedUser = userService.registerClient(signUpRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(addedUser.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully!"));
    }

    @PostMapping("/update-token")
    public ResponseEntity<AuthTokenResponse> updateToken(HttpServletRequest request) {

        final AuthTokenResponse authTokenResponse = tokenProvider.updateAuthToken(request);

        return ResponseEntity.ok(authTokenResponse);
    }

    @GetMapping("/check-email-availability")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userService.existsByEmail(email);
        return ResponseEntity.ok(
                new UserIdentityAvailability(isAvailable)
        );
    }

    @RequestMapping(value = "/confirm-account",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ApiResponse> confirmUserAccount(@RequestParam("token") String confirmationToken) {

        userService.confirmUserAccount(confirmationToken);

        return ResponseEntity.ok(
                new ApiResponse(true, "Account confirmed successfully")
        );
    }

}
