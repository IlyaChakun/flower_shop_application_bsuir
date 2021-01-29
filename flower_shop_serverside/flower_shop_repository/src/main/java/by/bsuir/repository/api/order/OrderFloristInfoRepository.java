package by.bsuir.repository.api.order;

import by.bsuir.entity.order.OrderFloristInfo;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderFloristInfoRepository extends AbstractRepository<OrderFloristInfo> {

    Optional<OrderFloristInfo> findByOrderId(Long orderId);

}
