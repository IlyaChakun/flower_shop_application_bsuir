package by.bsuir.entity.product.bouqet;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bouquet_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BouquetType extends BaseAbstractEntity {

    @Column(name = "bouquet_type")
    private String bouquetType;

}
