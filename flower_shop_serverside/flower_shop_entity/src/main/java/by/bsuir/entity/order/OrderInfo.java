package by.bsuir.entity.order;

import by.bsuir.entity.user.Client;
import by.bsuir.entity.user.Courier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderInfo {

    @ManyToOne(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Courier courier;

    private OrderCosts orderCosts;

    private OrderDeliveryAddress orderAddress;

    private OrderPaymentType orderPaymentType;

    @Column(name = "date_of_complete")
    private LocalDateTime dateOfComplete;


}
