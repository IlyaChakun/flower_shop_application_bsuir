package by.bsuir.entity.cart;

import by.bsuir.entity.BaseAbstractEntity;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.common.FlowerLengthCost;
import by.bsuir.entity.user.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@Getter
@Setter
public class CartItem extends BaseAbstractEntity {

    @ManyToOne
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    private AbstractFlowerProduct product;

    @OneToOne(cascade = CascadeType.ALL)
    private FlowerLengthCost flowerLengthCost;

    @Column(name = "quantity")
    private Integer quantity;

}
