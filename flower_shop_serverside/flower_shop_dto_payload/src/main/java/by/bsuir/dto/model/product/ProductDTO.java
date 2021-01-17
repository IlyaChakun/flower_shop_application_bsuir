package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ImageDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends AbstractDTO {

    private Long shopId;

    private ProductTypeDTO productType;

    private String title;

    private String description;

    private Integer availableAmountOnStock;

    private List<FlowerLengthCostDTO> flowerLengthCosts = new ArrayList<>();

    private CountryDTO country;  // страна происхождения

    private ImageDTO image;

}
