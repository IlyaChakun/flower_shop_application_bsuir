package by.bsuir.entity.flower;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flowers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AbstractFlowerEntity extends AbstractEntity {

    private FlowerType flowerType; // роза или тюльпан

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;  // страна происхождения

    @ManyToOne
    private FlowerSort flowerSort;   // сорт цветка

    @ManyToOne(cascade = CascadeType.ALL)
    private Set<Color> color = new HashSet<>();

    private Set<FlowerLengthCost> flowerLengthCost = new HashSet<>();

//    private Image image;

    @Column(name = "description", length = 512)
    private String description;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Review> reviews = new HashSet<>();

}
