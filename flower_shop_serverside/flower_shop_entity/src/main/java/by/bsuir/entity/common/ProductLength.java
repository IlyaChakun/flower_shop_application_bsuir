package by.bsuir.entity.common;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_lengths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductLength extends BaseAbstractEntity {

    @Column(name = "product_length")
    private Integer productLength;

}
