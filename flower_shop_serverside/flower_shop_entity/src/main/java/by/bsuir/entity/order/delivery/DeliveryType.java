package by.bsuir.entity.order.delivery;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryType extends BaseAbstractEntity {

    @Column(name = "delivery_type_name")
    private String deliveryTypeName;

}
