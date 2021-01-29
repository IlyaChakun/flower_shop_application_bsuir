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
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseAbstractEntity {

    @Column(name = "city_name", nullable = false, length = 48)
    private String cityName;

}
