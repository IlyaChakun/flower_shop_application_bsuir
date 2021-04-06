package by.bsuir.dto.model.order.buynow;

import by.bsuir.dto.model.order.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@Getter
@Setter
public class BuyNowOrderDTO extends OrderDTO {


    @NotBlank(message = "Name can`t be null or spaces")
    @Pattern(regexp = ".{5,30}", message = "Name can`t be smaller then 5 symbols and bigger then 30 symbols")
    private String name;

    @NotBlank(message = "email can`t be null or spaces")
    @Email(message = "Email should be correct!")
    private String email;


    private String phoneNumber;

}
