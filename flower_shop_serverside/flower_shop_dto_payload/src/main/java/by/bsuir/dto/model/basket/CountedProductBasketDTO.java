package by.bsuir.dto.model.basket;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class CountedProductBasketDTO extends BaseAbstractDTO {

    private Long clientId;

    private AbstractFlowerProductDTO product;

    private Integer quantity;
}
