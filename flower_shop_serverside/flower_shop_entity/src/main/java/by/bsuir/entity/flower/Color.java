package by.bsuir.entity.flower;

import by.bsuir.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Color extends AbstractEntity {

    @Column(name = "color_name", nullable = false)
    private String colorName;
}
