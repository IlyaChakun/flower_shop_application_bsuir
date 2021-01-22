package by.bsuir.entity.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {


    @ManyToOne
    private Region region;

    @ManyToOne
    private City city;

    private String address;


}
