package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ImageDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerLengthCostDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Getter
@Setter
@NoArgsConstructor
public class AbstractFlowerProductDTO extends AbstractDTO {

//    @Valid
//    @NotNull(message = "flowerColors must be defined")
//    @Size(min = 1, message = "Min one flower color")
//    private List<FlowerColorDTO> flowerColors = new ArrayList<>();

    @Valid
    @NotNull(message = "flowerLengthCosts must be defined")
    @Size(min = 1, message = "Min one length cost")
    private List<FlowerLengthCostDTO> flowerLengthCosts = new ArrayList<>();

//    @Valid
//    @NotNull(message = "flowerSorts must be defined")
//    @Size(min = 1, message = "Min one flower sort")
//    private List<FlowerSortDTO> flowerSorts = new ArrayList<>();


    private FlowerSortDTO flowerSort;

    private FlowerColorDTO flowerColor;

    @Valid
    private CountryDTO country;  // страна происхождения

    private ImageDTO image;

    @Size(max = 512, message = "Описание для цветов не более 512 символов!")
    private String description;

    @NotNull(message = "Количество продукта должно быть выбрано")
    @Min(value = 1, message = "Количество цветок должно быть больше 0 и меньше 500_000!")
    @Max(value = 500_000, message = "Количество цветок должно быть больше 0 и меньше 500_000!")
    private Integer availableAmountOnStock;

    @NotNull(message = "Магазин должен быть выбран")
    @JsonManagedReference
    private ShopDTO shop;

}
