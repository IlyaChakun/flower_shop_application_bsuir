package by.bsuir.entity.cart;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@Getter
@Setter
public class CartItem extends BaseAbstractEntity {

    @Column(name = "basket_id")
    private Long basketId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_length_cost_id")
    private Long productLengthCostId;

    @Column(name = "quantity")
    private Integer quantity;

}