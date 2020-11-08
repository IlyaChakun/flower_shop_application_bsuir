package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.product.common.FlowerColor;
import by.bsuir.entity.product.common.FlowerLengthCost;
import by.bsuir.entity.product.common.FlowerSort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AbstractFlowerProductDTO extends AbstractDTO {


    private List<FlowerColor> flowerColors = new ArrayList<>();
    private List<FlowerLengthCost> flowerLengthCosts = new ArrayList<>();
    private List<FlowerSort> flowerSorts = new ArrayList<>();
    private Country country;  // страна происхождения

//    private Image image;

    private String description;

}
