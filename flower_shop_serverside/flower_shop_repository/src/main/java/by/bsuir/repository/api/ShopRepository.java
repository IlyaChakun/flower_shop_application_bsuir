package by.bsuir.repository.api;

import by.bsuir.entity.company.Shop;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends AbstractRepository<Shop> {

    Optional<Shop> findByContactsCityAndContactsAddress(String city, String address);

}
