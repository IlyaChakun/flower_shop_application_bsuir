package by.bsuir.entity.flower;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.PaymentType;
import by.bsuir.entity.Review;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.common.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flower_bouquets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerBouquet extends AbstractEntity {

    private FlowerType flowerType; // роза или тюльпан

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;  // страна происхождения

    @ManyToOne
    private FlowerSort flowerSort;   // сорт цветка

    @ManyToOne(cascade = CascadeType.ALL)
    private Color color;

    private Set<FlowerLengthCost> flowerLengthCost = new HashSet<>();


    private Image image;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    private PaymentType paymentType;


}
