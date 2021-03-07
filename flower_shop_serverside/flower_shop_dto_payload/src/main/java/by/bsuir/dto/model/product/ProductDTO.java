package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends AbstractDTO {

//    @NotNull(message = "Shop id must be selected")
//    private Long shopId;

    @NotNull(message = "Category id must be selected")
    private Long categoryId;

    @NotNull(message = "Country id must be selected")
    private Long countryId;  // страна происхождения

    @NotBlank(message = "Title must not be blank")
    @Size(min = 4, max = 64, message = "Title can`t be more than 64 symbols or low than 4 symbols")
    private String title;

    @Size(max = 512, message = "Description can`t be bigger than 512 symbols")
    private String description;

    @Min(value = 1, message = "Available amount must not be lower than 1")
    @Max(value = 5000, message = "Available amount must not be greater than 5000")
    private Integer availableAmount;

    @Valid
    private List<ProductLengthCostDTO> productLengthCost = new ArrayList<>();


//    private ImageDTO image;

}
