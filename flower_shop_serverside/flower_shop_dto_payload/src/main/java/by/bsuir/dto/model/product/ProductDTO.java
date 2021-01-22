package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.entity.common.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends AbstractDTO {

    private Long shopId;

    private Long categoryId;

    private String title;

    private String description;

    private Integer availableAmountOnStock;

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


    private CountryDTO producer;  // страна происхождения

    private Image image;

}
