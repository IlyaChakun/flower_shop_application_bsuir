package by.bsuir.dto.model.cart;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO extends BaseAbstractDTO {

    @Valid
    private List<CartItemDTO> cartItems = new ArrayList<>();

    @DecimalMin(value = "0.0", message = "Общая сумма корзины не может быть меньше 0!")
    private Double totalPrice;

    @Min(value = 0, message = "Общее кол-во продуктов в корзине не может быть меньше 0!")
    private Integer totalElements;

    @NotNull(message = "Ид магазина не может быть пустым")
    private Long shopId;

}
