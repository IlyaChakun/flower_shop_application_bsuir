package by.bsuir.entity.product.common;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerColor extends BaseAbstractEntity {

    @Column(name = "color_name", nullable = false)
    private String colorName;

}
