package by.bsuir.dto.model.order.delivery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class OrderDeliveryInfoDTO {

    @Size(min = 0, max = 50, message = "Address can`t be bigger than 50")
    private String address;

    private Integer floorNumber;

    private Integer entranceNumber;//номер подъезда

    private DeliveryTypeDTO deliveryType;

}
