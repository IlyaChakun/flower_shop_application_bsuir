package by.bsuir.flowershop.repository.api.shop;

import by.bsuir.flowershop.entity.shop.Shop;
import by.bsuir.flowershop.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends AbstractRepository<Shop> {

    Optional<Shop> findByContactsCityIdAndContactsAddress(Long cityId, String address);

}
