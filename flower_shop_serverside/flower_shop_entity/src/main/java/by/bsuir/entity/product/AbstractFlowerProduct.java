package by.bsuir.entity.product;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.product.common.FlowerColor;
import by.bsuir.entity.product.common.FlowerLengthCost;
import by.bsuir.entity.product.common.FlowerSort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flower_products")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractFlowerProduct extends AbstractEntity {

    @ManyToMany
    @JoinTable(name = "flower_colors",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "flower_id",
                                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "color_id",
                    referencedColumnName = "id")})
    private List<FlowerColor> flowerColors = new ArrayList<>();

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

    @ManyToMany
    @JoinTable(name = "flower_sorts",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "flower_id",
                                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "flower_sort_id",
                    referencedColumnName = "id")})
    private List<FlowerSort> flowerSorts = new ArrayList<>();

    //TODO у многих цветов много сортов??...может всеже многие-к-одному


    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;  // страна происхождения

//    private Image image;

    @Column(name = "description", length = 512)
    private String description;


    @Column(name = "available_amount_on_stock")
    private Integer availableAmountOnStock;


    @ManyToOne
    private Shop shop;

}
