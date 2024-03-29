package by.bsuir.repository.api.order;

import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseOrderRepository<Order> {

    Page<Order> findAllByClientIdAndOrderStatus(Pageable pageable, Long clientId, OrderStatus orderStatus);

    Page<Order> findAllByOrderStatus(Pageable pageable, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusAndAndDateOfLastUpdateIsBetween(
            OrderStatus orderStatus,
            LocalDateTime startDate,
            LocalDateTime endDate);

    List<Order> findAllByOrderFloristInfoFloristId(Long floristId);

    List<Order> findAllByOrderFloristInfoFloristIdAndOrderFloristInfoFloristCompletionTimeIsBetween(
            Long floristId,
            LocalDateTime startDate,
            LocalDateTime endDate);

    Integer countOrderByClientId(Long clientId);

}
