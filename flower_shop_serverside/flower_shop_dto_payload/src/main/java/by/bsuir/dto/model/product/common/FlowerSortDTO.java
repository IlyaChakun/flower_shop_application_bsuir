package by.bsuir.dto.model.product.common;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlowerSortDTO extends BaseAbstractDTO {

    //@NotBlank(message = "Название сорта не может быть пустым")
    private String sortNameRu;

    // @Size(max = 64, message = "Название сорта на английском не может быть более 64 символов")
    private String sortNameEn;

}
