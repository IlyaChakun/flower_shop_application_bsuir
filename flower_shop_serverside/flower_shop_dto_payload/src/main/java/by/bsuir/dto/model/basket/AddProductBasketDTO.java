package by.bsuir.dto.model.basket;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class AddProductBasketDTO extends BaseAbstractDTO {

    private Long userId;

    @NotNull(message = "productId must be set")
    private Long productId;

}
