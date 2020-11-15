package by.bsuir.entity.product.common;

import by.bsuir.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sorts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerSort extends AbstractEntity {

    @Column(name = "sort_name_ru", nullable = false)
    private String sortNameRu;

    @Column(name = "sort_name_en", nullable = false)
    private String sortNameEn;

}
