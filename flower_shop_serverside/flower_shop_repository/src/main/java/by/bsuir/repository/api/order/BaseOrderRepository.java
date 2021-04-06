package by.bsuir.repository.api.order;

import by.bsuir.entity.order.Order;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseOrderRepository<T extends Order> extends AbstractRepository<T> {
}
