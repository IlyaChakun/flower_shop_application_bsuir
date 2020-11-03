package by.bsuir.entity.product.flower;

import by.bsuir.entity.common.Country;
import by.bsuir.entity.product.AbstractFlowerProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flowers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flower extends AbstractFlowerProduct {

    @ManyToOne
    private FlowerType flowerType; // розы, цветы и зелень

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;  // страна происхождения


}
