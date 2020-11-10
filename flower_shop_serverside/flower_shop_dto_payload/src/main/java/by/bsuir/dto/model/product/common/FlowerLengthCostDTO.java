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

@NoArgsConstructor
@Getter
@Setter
public class FlowerLengthCostDTO extends BaseAbstractDTO {

    private Double stemLength;

    private Double price;
}
