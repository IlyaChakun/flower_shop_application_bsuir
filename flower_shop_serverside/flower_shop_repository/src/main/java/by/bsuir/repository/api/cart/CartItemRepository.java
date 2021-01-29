package by.bsuir.repository.api.cart;

import by.bsuir.entity.cart.CartItem;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends AbstractRepository<CartItem> {
}