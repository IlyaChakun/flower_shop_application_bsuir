package by.bsuir.dto.model.order;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Getter
@Setter
@NoArgsConstructor
public class OrderCostsDTO extends BaseAbstractDTO {

    @DecimalMin(value = "0", message = "Общая стоимость заказа не может быть меньше 0 руб!")
    @DecimalMax(value = "15 000 000", message = "Общая стоимость заказа не может быть больше 15 000 000 руб!")
    private Double totalAmount;

}
