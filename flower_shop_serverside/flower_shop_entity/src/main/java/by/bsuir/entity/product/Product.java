package by.bsuir.entity.product;

import by.bsuir.entity.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity {

//    @Column(name = "shop_id")
//    private Long shopId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "country_id", nullable = false)
    private Long countryId;  // страна происхождения

    @Column(name = "title", length = 64, nullable = false)
    private String title;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "available_amount")
    private Integer availableAmount;

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "product_length_costs",
//            joinColumns =
//                    {
//                            @JoinColumn(
//                                    name = "product_id",
//                                    referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "length_cost_id",
//                    referencedColumnName = "id")})
    @OneToMany(
            cascade = {
                    CascadeType.ALL
            },
            fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "product_length_costs",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "length_cost_id")
//    )
    private List<ProductLengthCost> productLengthCost = new ArrayList<>();


//    private Image image;
}
