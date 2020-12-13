package by.bsuir.service.notification.api;

import by.bsuir.dto.model.notification.OrderNotificationDTO;

import java.util.List;

public interface OrderNotificationService {

    List<OrderNotificationDTO> findAll();

    List<OrderNotificationDTO> findAllWithStatusNew();

    Integer getCompanyNewOrdersCount();

}
