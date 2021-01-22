package by.bsuir.entity.order;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
@NoArgsConstructor
@Getter
@Setter
public class OrderProduct extends BaseAbstractEntity {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_length_cost_id")
    private Long productLengthCostId;

    @Column(name = "quantity")
    private Integer quantity;

}