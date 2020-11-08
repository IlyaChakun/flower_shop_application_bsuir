package by.bsuir.dto.model.product.bouquet;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
public class BouquetTypeDTO extends AbstractDTO {

    private String bouquetType;
}