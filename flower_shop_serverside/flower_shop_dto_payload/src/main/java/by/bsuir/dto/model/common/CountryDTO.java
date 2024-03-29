package by.bsuir.dto.model.common;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CountryDTO extends BaseAbstractDTO {

    private String countryNameRu;

    private String countryNameEn;
}

