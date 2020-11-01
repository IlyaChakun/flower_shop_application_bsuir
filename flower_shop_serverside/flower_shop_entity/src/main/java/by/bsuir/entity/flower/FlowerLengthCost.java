package by.bsuir.entity.flower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "flower_length_cost")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerLengthCost {

    @Column(name = "stemLength", nullable = false)
    private Double stemLength;

    @Column(name = "price", nullable = false)
    private Double price;
}
