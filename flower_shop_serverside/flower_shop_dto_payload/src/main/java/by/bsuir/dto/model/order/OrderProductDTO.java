package by.bsuir.dto.model.order;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.entity.product.AbstractFlowerProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderProductDTO extends AbstractDTO {

    private AbstractFlowerProduct product;

    private Integer quantity;

}
