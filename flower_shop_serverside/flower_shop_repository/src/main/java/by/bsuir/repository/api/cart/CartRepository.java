package by.bsuir.repository.api.cart;

import by.bsuir.entity.cart.Cart;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends AbstractRepository<Cart> {

    Cart getByClientId(Long id);

    Boolean existsByClientId(Long id);

    void deleteByClientId(Long clientId);

}
