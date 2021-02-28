package by.bsuir.dto.model.product;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductLengthCostDTO extends BaseAbstractDTO {

    @NotNull(message = "Stem length must be set")
    @Min(value = 40, message = "Stem length can`t be lower than 40sm")
    @Max(value = 200, message = "Stem length can`t be bigger then 200sm")
    private Integer stemLength;

    @NotNull(message = "Cost must be set")
    @DecimalMin(value = "1", message = "Cost can`t be lower 1 byn")
    @DecimalMax(value = "50000", message = "Cost can`t be bigger than 50_000 byn")
    private Double cost;

}
