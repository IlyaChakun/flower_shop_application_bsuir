package by.bsuir.entity.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderDeliveryAddress {

    @Column(name = "address")
    private String address;

    @Column(name = "floor_number")
    private Short floorNumber;

    @Column(name = "entrance_number")
    private Short entranceNumber;//номер подъезда

}
