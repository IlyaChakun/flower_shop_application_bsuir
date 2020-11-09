package by.bsuir.dto.model.product.common;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@NoArgsConstructor
@Getter
@Setter
public class FlowerLengthCostDTO extends BaseAbstractDTO {

    @DecimalMin(value = "0", message = "Длина стебля не может быть меньше 30 см!")
    @DecimalMax(value = "150", message = "Стоимость стебля не может быть больше 150 см!")
    private Double stemLength;

    @DecimalMin(value = "0", message = "Стоимость не может быть меньше 0 руб!")
    @DecimalMax(value = "1500", message = "Стоимость не может быть больше 1500 руб!")
    private Double price;
}
