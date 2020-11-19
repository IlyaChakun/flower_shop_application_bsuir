package by.bsuir.repository.api;

import by.bsuir.entity.common.Country;
import by.bsuir.entity.company.Contacts;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends AbstractRepository<Country> {

}
