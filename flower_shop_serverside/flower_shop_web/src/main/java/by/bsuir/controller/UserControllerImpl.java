package by.bsuir.controller;

import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.dto.validation.annotation.PositiveLong;
import by.bsuir.security.core.CurrentUser;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.security.dto.ApiResponse;
import by.bsuir.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static by.bsuir.controller.ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid;

@RestController
@AllArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    public ResponseEntity<?> me(@CurrentUser UserPrincipal userPrincipal) {

        log.info("get me works");

        final String userEmail = userPrincipal.getEmail();

        if (userService.existsByEmail(userEmail)) {
            return ResponseEntity.ok(userService.getByEmail(userEmail));
        }

        return ResponseEntity.ok(new ApiResponse(false, "user does not exist!"));
    }

    @Override
    public ResponseEntity<?> updateProfile(@PathVariable("id") @PositiveLong String id,
                                           @CurrentUser UserPrincipal userPrincipal,
                                           @RequestBody @Valid UserDTO userDTO,
                                           BindingResult bindingResult) {

        log.info("in updateProfile method");

        checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        return ResponseEntity.ok(userService.update(userDTO, userPrincipal.getId()));

    }

}
