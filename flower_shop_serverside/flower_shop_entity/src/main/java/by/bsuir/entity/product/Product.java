package by.bsuir.entity.product;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.common.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity {

    @Column(name = "shop_id")
    private Long shopId;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductType productType;

    @Column(name = "title", length = 512)
    private String title;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "available_amount_on_stock")
    private Integer availableAmountOnStock;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "flower_length_costs",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "flower_id",
                                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "length_cost_id",
                    referencedColumnName = "id")})
    private List<FlowerLengthCost> flowerLengthCosts = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;  // страна происхождения

    private Image image;

}
