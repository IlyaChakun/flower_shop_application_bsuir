package by.bsuir.entity.order.delivery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderDeliveryInfo {

    @Column(name = "address")
    private String address;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "entrance_number")
    private Integer entranceNumber;//номер подъезда

    @ManyToOne(cascade = CascadeType.ALL)
    private DeliveryType deliveryType;


}
