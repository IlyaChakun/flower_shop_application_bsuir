package by.bsuir.repository.api.order;

import by.bsuir.entity.order.usual.UsualOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UsualOrderRepository extends BaseOrderRepository<UsualOrder> {

    Page<UsualOrder> findAllByClientId(Pageable pageable, Long clientId);
}
