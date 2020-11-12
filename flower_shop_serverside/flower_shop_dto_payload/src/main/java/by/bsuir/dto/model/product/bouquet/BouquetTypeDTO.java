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
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class BouquetTypeDTO extends AbstractDTO {

    @Size(max = 64, message = "Тип букета не может быть более 64 символов")
    private String bouquetType;
}
