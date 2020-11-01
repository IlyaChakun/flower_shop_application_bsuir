package by.bsuir.entity.common;

import by.bsuir.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Country extends AbstractEntity {

    @Column(name = "country_name_ru", nullable = false, length = 48)
    private String countryNameRu;

    @Column(name = "country_name_en", nullable = false, length = 48)
    private String countryNameEn;
}

