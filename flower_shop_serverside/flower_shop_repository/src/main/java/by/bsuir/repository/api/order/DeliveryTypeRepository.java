package by.bsuir.repository.api.order;

import by.bsuir.entity.order.delivery.DeliveryType;
import by.bsuir.repository.api.core.AbstractRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends AbstractRepository<DeliveryType> {
    Optional<DeliveryType> findByDeliveryTypeName(String name);
}
