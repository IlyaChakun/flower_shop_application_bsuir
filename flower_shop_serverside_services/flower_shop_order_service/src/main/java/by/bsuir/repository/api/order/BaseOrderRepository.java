package by.bsuir.repository.api.order;

import by.bsuir.entity.order.BaseOrder;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseOrderRepository<T extends BaseOrder> extends AbstractRepository<T> {
}
