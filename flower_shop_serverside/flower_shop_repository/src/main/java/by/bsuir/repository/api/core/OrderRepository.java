package by.bsuir.repository.api.core;

import by.bsuir.entity.order.Order;
import by.bsuir.entity.user.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends AbstractRepository<Order> {

    Page<Order> findAllByClient(Pageable pageable, Client client);

    Optional<Order> findByIdAndOrderInfoClientId(Long orderId, Long clientId);

}
