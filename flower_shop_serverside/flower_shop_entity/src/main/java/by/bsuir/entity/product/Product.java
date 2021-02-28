package by.bsuir.entity.product;

import by.bsuir.entity.AbstractEntity;
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

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "producer_id", nullable = false)
    private Long producerId;  // страна происхождения

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
