package by.bsuir.entity.flower;

import by.bsuir.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerType extends AbstractEntity {

    @Column(name = "flower_type")
    private String flowerType;
}
