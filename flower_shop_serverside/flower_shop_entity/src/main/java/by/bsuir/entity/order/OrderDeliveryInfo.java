package by.bsuir.entity.order;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDeliveryInfo {

    private OrderDeliveryAddress orderDeliveryAddress;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "begin_delivery_time")
    private LocalTime beginDeliveryTime;

    @Column(name = "end_delivery_time")
    private LocalTime endDeliveryTime;

}
