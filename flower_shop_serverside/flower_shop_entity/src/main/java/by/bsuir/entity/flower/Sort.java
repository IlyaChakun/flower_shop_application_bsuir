package by.bsuir.entity.flower;

import by.bsuir.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sorts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sort extends AbstractEntity {

    @Column(name = "sort_name", nullable = false)
    private String sortName;
}
