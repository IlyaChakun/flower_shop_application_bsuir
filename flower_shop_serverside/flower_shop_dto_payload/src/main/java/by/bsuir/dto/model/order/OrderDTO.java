package by.bsuir.dto.model.order;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.entity.order.OrderStatus;
import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends AbstractDTO {

    private Set<OrderProductDTO> orderProducts = new HashSet<>();

    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться

    private String comment;

    private ClientDTO client;

    private OrderStatus orderStatus;

    private Double totalAmount;

    private String address;

    private Integer floorNumber;

    private Integer entranceNumber;//номер подъезда

    private Long shopId;

}
