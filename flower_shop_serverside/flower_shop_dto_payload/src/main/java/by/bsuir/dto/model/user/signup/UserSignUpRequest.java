package by.bsuir.dto.model.user.signup;

import by.bsuir.dto.model.common.ImageDTO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "Role must be chosen")
    private String roleType;//ROLE_CLIENT ROLE_STOCK_OWNER ROLE_STOCK_WORKER

    @NotBlank(message = "Name can`t be null or spaces")
    @Pattern(regexp = ".{2,128}", message = "Name can`t be smaller then 2 symbols and bigger then 128 symbols")
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

    private ImageDTO image;

    private String phoneNumber;

}
