package by.bsuir.dto.model.common;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CountryDTO extends AbstractDTO {

    private String countryNameRu;

    private String countryNameEn;
}

