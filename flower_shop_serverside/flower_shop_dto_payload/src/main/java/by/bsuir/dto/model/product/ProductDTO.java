package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.ImageDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends AbstractDTO {

    @NotNull(message = "Shop id must be selected")
    private Long shopId;

    @NotNull(message = "Category id must be selected")
    private Long categoryId;

    @NotNull(message = "Producer id must be selected")
    private Long producerId;  // страна происхождения

    @NotBlank(message = "Title must not be blank")
    @Size(min = 4, max = 64, message = "Title can`t be more than 64 symbols or low than 4 symbols")
    private String title;

    @Size(max = 512, message = "Description can`t be bigger than 512 symbols")
    private String description;

    @Min(value = 1, message = "Available amount must not be lower than 1")
    @Max(value = 5000, message = "Available amount must not be greater than 5000")
    private Integer availableAmountOnStock;

    @Min(value = 1, message = "Cost must not be lower than 1 byn")
    @Max(value = 5000, message = "Cost must not be greater than 5000 byn")
    private Double cost;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "flower_length_costs",
//            joinColumns =
//                    {
//                            @JoinColumn(
//                                    name = "flower_id",
//                                    referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "length_cost_id",
//                    referencedColumnName = "id")})
//    private List<FlowerLengthCost> flowerLengthCosts = new ArrayList<>();


    private ImageDTO image;

}
