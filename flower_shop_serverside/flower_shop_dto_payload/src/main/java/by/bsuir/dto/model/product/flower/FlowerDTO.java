package by.bsuir.dto.model.product.flower;

import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlowerDTO extends AbstractFlowerProductDTO {

    private FlowerTypeDTO flowerType; // розы, цветы и зелень

}
