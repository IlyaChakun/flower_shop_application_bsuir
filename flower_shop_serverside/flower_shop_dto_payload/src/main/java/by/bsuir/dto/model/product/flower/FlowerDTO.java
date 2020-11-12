package by.bsuir.dto.model.product.flower;

import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.flower.FlowerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@NoArgsConstructor
@Getter
@Setter
public class FlowerDTO extends AbstractFlowerProductDTO {

    private FlowerTypeDTO flowerType; // розы, цветы и зелень

}
