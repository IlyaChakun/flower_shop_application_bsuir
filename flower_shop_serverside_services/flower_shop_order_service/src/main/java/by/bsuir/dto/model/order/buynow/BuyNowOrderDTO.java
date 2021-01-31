package by.bsuir.dto.model.order.buynow;

import by.bsuir.dto.model.order.BaseOrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Getter
@Setter
public class BuyNowOrderDTO extends BaseOrderDTO {

    @NotBlank(message = "First name can`t be blank or spaces")
    @Size(min = 2, max = 64, message = "First name must be in 2 from 64")
    private String firstName;

    @NotBlank(message = "Last name can`t be blank or spaces")
    @Size(min = 2, max = 64, message = "Last name must be in 2 from 64")
    private String lastName;

    private String phoneNumber;

}
