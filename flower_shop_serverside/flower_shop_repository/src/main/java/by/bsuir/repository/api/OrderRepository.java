package by.bsuir.repository.api;

import by.bsuir.entity.order.Order;
import by.bsuir.entity.user.Client;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends AbstractRepository<Order> {

    Page<Order> findAllByClient(Pageable pageable, Client client);

    Optional<Order> findByIdAndClientId(Long orderId, Long clientId);

}
