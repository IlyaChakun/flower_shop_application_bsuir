package by.bsuir.dto.model.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class RequestCartItemDTO {

    @NotNull(message = "clientId must be set")
    private Long clientId;

    @NotNull(message = "productId must be set")
    private Long productId;

    @NotNull(message = "flowerLengthCostId must be set")
    private Long productLengthCostId;

    @NotNull(message = "product quantity must be set")
    @Min(value = 1, message = "Quantity can`t be <1")
    @Max(value = 99, message = "Quantity can`t be bigger then 99")
    private Integer quantity;

}
