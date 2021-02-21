package by.bsuir.remoteclients;

import by.bsuir.dto.user.UserDTO;
import by.bsuir.dto.user.UserSignUpRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "account-service")
public interface AccountClient {

    @PostMapping("/sign-up")
    UserDTO register(@RequestBody @Valid UserSignUpRequest signUpRequest);

}
