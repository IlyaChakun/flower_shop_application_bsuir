package by.bsuir.dto.model.order;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderProductDTO extends BaseAbstractDTO {

    private AbstractFlowerProductDTO product;

    private Integer quantity;

}
