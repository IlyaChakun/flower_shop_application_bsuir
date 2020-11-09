package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.product.common.FlowerColor;
import by.bsuir.entity.product.common.FlowerLengthCost;
import by.bsuir.entity.product.common.FlowerSort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AbstractFlowerProductDTO extends AbstractDTO {

    @Valid
    private List<FlowerColor> flowerColors = new ArrayList<>();

    @Valid
    private List<FlowerLengthCost> flowerLengthCosts = new ArrayList<>();

    @Valid
    private List<FlowerSort> flowerSorts = new ArrayList<>();

    @Valid
    private Country country;  // страна происхождения

//    private Image image;

    @Size(min = 0, max = 512, message = "Описание для цветов не более 512 символов!")
    private String description;

}
