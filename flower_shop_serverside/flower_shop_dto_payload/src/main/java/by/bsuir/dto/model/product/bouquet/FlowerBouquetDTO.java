package by.bsuir.dto.model.product.bouquet;

import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class FlowerBouquetDTO extends AbstractFlowerProductDTO {

    @Valid
    private BouquetTypeDTO bouquetType; //букеты из роз и тд

    @Size(max = 128, message = "Название букета не может быть более 128 символов")
    private String title;
}
