package by.bsuir.dto.model.order;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends AbstractDTO {
//todo
//    private Set<OrderProductDTO> orderProducts = new HashSet<>();

    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться

    private String comment;

    private Long clientId;

//    private OrderStatus orderStatus;

    private Double totalAmount;

    private String address;

    private Integer floorNumber;

    private Integer entranceNumber;//номер подъезда

}