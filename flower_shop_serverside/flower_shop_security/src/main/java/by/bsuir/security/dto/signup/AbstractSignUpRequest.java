package by.bsuir.security.dto.signup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class AbstractSignUpRequest {

    @NotBlank(message = "Name can`t be null or spaces")
    @Pattern(regexp = ".{5,30}", message = "Name can`t be smaller then 5 symbols and bigger then 30 symbols")
    private String name;

    @NotBlank(message = "email can`t be null or spaces")
    @Email(message = "Email should be correct!")
    private String email;

    @NotBlank(message = "Password can`t be null or spaces")
    @Pattern(regexp = ".{5,30}", message = "Pass can`t be smaller then 5 symbols and bigger then 30 symbols")
    private String password;

    @NotBlank(message = "Confirmed password can`t be null or spaces!")
    @Pattern(regexp = ".{5,30}", message = "Confirmed pass can`t be smaller then 5 symbols and bigger then 30 symbols")
    private String confirmedPassword;
//
//    private String imageUrl;

}
