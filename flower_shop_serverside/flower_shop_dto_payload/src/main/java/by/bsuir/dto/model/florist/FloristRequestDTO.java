package by.bsuir.dto.model.florist;

import by.bsuir.dto.model.user.signup.UserSignUpRequest;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FloristRequestDTO {

    @Valid
    private UserSignUpRequest userSignUpRequest;

    @DecimalMin(value = "0.5", message="Experience should more than 0.5 year")
    @DecimalMax(value = "50", message="Experience should be lower than 50 years")
    private Double experience;

    @DecimalMin(value = "250", message="Salary should more than 250 rub")
    @DecimalMax(value = "5000", message="Salary should be lower than 5000 rub")
    private Double salary;//оклад

    @NotNull(message = "Shop id is required")
    private Long shopId;

}
