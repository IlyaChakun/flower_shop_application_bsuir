package by.bsuir.entity.flower;

import by.bsuir.entity.*;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.common.Image;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "flowers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Flower extends AbstractEntity {

    @Column(name = "stemLength", nullable = false)
    private Double stemLength;

    private FlowerType flowerType;

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

    @ManyToOne
    private Sort sort;

    @ManyToOne(cascade = CascadeType.ALL)
    private Color color;

    @Column(name = "price", nullable = false)
    private Double price;

    private Image image;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    private PaymentType paymentType;


}
