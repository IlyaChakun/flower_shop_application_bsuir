package by.bsuir.dto.mapper.notification;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.notification.OrderNotificationDTO;
import by.bsuir.entity.ordernotification.OrderNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderNotificationMapper extends AbstractMapperDTO<OrderNotification, OrderNotificationDTO> {

    @Autowired
    public OrderNotificationMapper() {
        super(OrderNotification.class, OrderNotificationDTO.class);
    }
}