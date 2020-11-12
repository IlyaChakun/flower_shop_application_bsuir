package by.bsuir.dto.model.basket;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BasketDTO extends BaseAbstractDTO {

    private List<CountedProductBasketDTO> products;

    private BigDecimal totalPrice;

    private Integer totalElements;

}
