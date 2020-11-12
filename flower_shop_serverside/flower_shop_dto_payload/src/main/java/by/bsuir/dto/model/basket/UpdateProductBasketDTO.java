package by.bsuir.dto.model.basket;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class UpdateProductBasketDTO extends BaseAbstractDTO {

    @NotNull(message = "userId must be set")
    private Long userId;

    @NotNull(message = "productId must be set")
    private Long productId;

    @NotNull(message = "product quantity must be set")
    @Min(value = 0, message = "Quantity can`t be <0")
    @Max(value = 99, message = "Quantity can`t be bigger then 99")
    private Integer quantity;
}
