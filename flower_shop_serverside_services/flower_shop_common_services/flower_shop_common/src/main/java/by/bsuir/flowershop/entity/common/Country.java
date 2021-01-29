package by.bsuir.flowershop.entity.common;


import by.bsuir.flowershop.entity.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country extends BaseAbstractEntity {

    @Column(name = "country_name_ru", nullable = false, length = 48)
    private String countryNameRu;

    @Column(name = "country_name_en", nullable = false, length = 48)
    private String countryNameEn;

}
