package by.bsuir.flowershop.entity.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Dimensions { //габариты

    @Column(name = "width")
    private Double width;

    @Column(name = "length")
    private Double length;

    @Column(name = "height")
    private Double height;

}
