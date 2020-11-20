package by.bsuir.entity.product.flower;

import by.bsuir.entity.company.Shop;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.flower.FlowerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
