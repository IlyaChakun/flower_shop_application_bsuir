package by.bsuir.repository.api;

import by.bsuir.entity.company.Contacts;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactsRepository extends AbstractRepository<Contacts> {

    Optional<Contacts> findByCityAndAddress(String city, String address);


}
