package by.bsuir.entity.product;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity {

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "title", length = 64, nullable = false)
    private String title;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "available_amount_on_stock")
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


    private Long producerId;  // страна происхождения

    private Image image;
}
