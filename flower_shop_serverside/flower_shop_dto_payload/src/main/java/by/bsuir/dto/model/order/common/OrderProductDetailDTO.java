package by.bsuir.dto.model.order.common;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.model.product.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderProductDetailDTO extends BaseAbstractDTO {

    private ProductDTO product;

    private Integer quantity;
}