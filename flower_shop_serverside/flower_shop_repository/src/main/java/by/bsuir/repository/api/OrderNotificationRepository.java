package by.bsuir.repository.api;

import by.bsuir.entity.order.OrderStatus;
import by.bsuir.entity.ordernotification.OrderNotification;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderNotificationRepository extends AbstractRepository<OrderNotification> {

    List<OrderNotification> findAllByOrderOrderStatus(OrderStatus orderStatus);

}
