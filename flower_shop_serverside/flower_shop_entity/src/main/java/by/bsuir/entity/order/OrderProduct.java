package by.bsuir.entity.order;

import by.bsuir.entity.BaseAbstractEntity;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.common.FlowerLengthCost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
@NoArgsConstructor
@Getter
@Setter
public class OrderProduct extends BaseAbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private AbstractFlowerProduct product;

    @OneToOne(cascade = CascadeType.ALL)
    private FlowerLengthCost flowerLengthCost;

    @Column(name = "quantity")
    private Integer quantity;

}
