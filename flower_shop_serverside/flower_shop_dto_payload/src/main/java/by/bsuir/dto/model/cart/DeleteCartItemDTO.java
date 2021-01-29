package by.bsuir.dto.model.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class DeleteCartItemDTO {

    @NotNull(message = "clientId must be set")
    private Long clientId;

    @NotNull(message = "productId must be set")
    private Long productId;

}