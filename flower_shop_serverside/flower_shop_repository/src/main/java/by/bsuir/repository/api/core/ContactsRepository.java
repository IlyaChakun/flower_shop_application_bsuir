package by.bsuir.repository.api.core;

import by.bsuir.entity.company.Contacts;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactsRepository extends AbstractRepository<Contacts> {

    Optional<Contacts> findByCityAndAddress(String city, String address);


}
