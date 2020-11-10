package by.bsuir.dto.model.product.bouquet;

import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.product.bouqet.BouquetType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
public class FlowerBouquetDTO extends AbstractFlowerProductDTO {

    private BouquetTypeDTO bouquetType; //букеты из роз и тд

    private String title;
}
