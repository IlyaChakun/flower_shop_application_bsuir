package by.bsuir.entity.product;

import by.bsuir.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "product_types")
@Getter
@Setter
@NoArgsConstructor
public class ProductType extends AbstractEntity {

    private String type;

}
