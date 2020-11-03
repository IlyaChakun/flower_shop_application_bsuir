package by.bsuir.entity.common;

import by.bsuir.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
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

