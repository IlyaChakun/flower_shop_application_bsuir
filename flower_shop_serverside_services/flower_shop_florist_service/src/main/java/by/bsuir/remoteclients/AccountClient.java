package by.bsuir.remoteclients;

import by.bsuir.dto.user.UserDTO;
import by.bsuir.dto.user.UserSignUpRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "auth-service")
public interface AccountClient {

    //    @PostMapping("/sign-up")
    //@PostMapping("/users")
    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserDTO register(@RequestBody @Valid UserSignUpRequest signUpRequest);

}
