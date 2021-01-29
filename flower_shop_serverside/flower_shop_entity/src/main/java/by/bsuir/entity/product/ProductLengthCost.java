package by.bsuir.entity.product;
import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_length_costs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductLengthCost extends BaseAbstractEntity {

    @Column(name = "stemLength", nullable = false)
    private Double stemLength;

    @Column(name = "cost", nullable = false)
    private Double cost;

}
