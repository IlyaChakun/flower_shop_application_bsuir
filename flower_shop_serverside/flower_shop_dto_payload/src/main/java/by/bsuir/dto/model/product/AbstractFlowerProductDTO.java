package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerLengthCostDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
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
    private List<FlowerColorDTO> flowerColors = new ArrayList<>();

    @Valid
    private List<FlowerLengthCostDTO> flowerLengthCosts = new ArrayList<>();

    @Valid
    private List<FlowerSortDTO> flowerSorts = new ArrayList<>();

    @Valid
    private CountryDTO country;  // страна происхождения

//    private Image image;

    @Size(min = 0, max = 512, message = "Описание для цветов не более 512 символов!")
    private String description;

}
