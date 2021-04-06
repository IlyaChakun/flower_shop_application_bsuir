package by.bsuir.repository.specification;

import by.bsuir.entity.order.Order;
import by.bsuir.entity.order.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

public final class OrderSpecification {

    private OrderSpecification() {

    }

    public static Specification<Order> findByClientId(Long clientId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .equal(root.get("clientId"), clientId);
    }

    public static Specification<Order> findByOrderStatus(OrderStatus orderStatus) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .equal(root.get("orderStatus"), orderStatus);
    }
}
