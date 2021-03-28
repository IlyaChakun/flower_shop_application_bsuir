package by.bsuir.dto.model.cart;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DeleteCartItemDTO {

    @NotNull(message = "clientId must be set")
    private Long clientId;

    @NotNull(message = "productId must be set")
    private Long productId;

    @NotNull(message = "productLengthCostId must be set")
    private Long productLengthCostId;

}