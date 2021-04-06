package by.bsuir.dto.model.florist;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.UserDTO;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FloristDTO extends AbstractDTO {

    @Valid
    private UserDTO user;

    @NotNull(message = "Shop id is required")
    private Long shopId; //флорист относится к конкретному магазину

    @DecimalMin(value = "0.5", message = "Experience should more than 0.5 year")
    @DecimalMax(value = "50", message = "Experience should be lower than 50 years")
    private Double experience;

    @DecimalMin(value = "250", message = "Salary should more than 250 rub")
    @DecimalMax(value = "5000", message = "Salary should be lower than 5000 rub")
    private Double salary;//оклад

    @Null(message = "statistic wil set automatically")
    private FloristStatisticDTO floristStatistic;
}
