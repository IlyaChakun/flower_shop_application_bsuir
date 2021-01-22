package by.bsuir.repository.api.shop;

import by.bsuir.entity.shop.Shop;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends AbstractRepository<Shop> {

    Optional<Shop> findByContactsCityIdAndContactsAddress(Long cityId, String address);

}
