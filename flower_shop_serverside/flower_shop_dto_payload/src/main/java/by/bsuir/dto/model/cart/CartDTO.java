package by.bsuir.dto.model.cart;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CartDTO extends BaseAbstractDTO {

    @Valid
    private List<CartItemDTO> cartItems;

    @DecimalMin(value = "0.0", message = "Общая сумма корзины не может быть меньше 0!")
    private BigDecimal totalPrice;

    @Min(value = 0, message = "Общее кол-во продуктов в корзине не может быть меньше 0!")
    private Integer totalElements;

}
