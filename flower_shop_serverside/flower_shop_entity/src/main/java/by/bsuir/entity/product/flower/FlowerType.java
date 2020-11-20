package by.bsuir.entity.product.flower;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "flower_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerType extends BaseAbstractEntity {

    @Column(name = "flower_type")
    private String flowerType;

}
