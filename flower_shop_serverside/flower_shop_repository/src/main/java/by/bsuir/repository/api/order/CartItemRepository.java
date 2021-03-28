package by.bsuir.repository.api.order;

import by.bsuir.entity.cart.CartItem;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends AbstractRepository<CartItem> {

    Optional<CartItem> findByClientIdAndProductIdAndProductLengthCostId(Long clientId, Long productId, Long productLengthCostId);

}
