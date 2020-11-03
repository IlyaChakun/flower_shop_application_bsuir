package by.bsuir.entity.product.bouqet;

import by.bsuir.entity.product.AbstractFlowerProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flower_bouquets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerBouquet extends AbstractFlowerProduct {

    @ManyToOne
    private BouquetType bouquetType; //букеты из роз и тд

    @Column(name = "title", nullable = false, length = 64)
    private String title;


}
