package by.bsuir.dto.model.basket;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class AddUpdateProductBasketDTO extends BaseAbstractDTO {

    private Long clientId;

    @NotNull(message = "productId must be set")
    private Long productId;

    @NotNull(message = "product quantity must be set")
    @Min(value = 0, message = "Quantity can`t be <0")
    @Max(value = 99, message = "Quantity can`t be bigger then 99")
    private Integer quantity;

}
