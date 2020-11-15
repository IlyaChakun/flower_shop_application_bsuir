package by.bsuir.repository.api.core;

import by.bsuir.entity.common.Country;
import by.bsuir.entity.company.Contacts;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends AbstractRepository<Country> {

}
