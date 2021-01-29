package by.bsuir.dto.model.cart;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CartItemDTO extends BaseAbstractDTO {

    private Long basketId;

    private Long productId;

    private Long productLengthCostId;

    private Integer quantity;

}