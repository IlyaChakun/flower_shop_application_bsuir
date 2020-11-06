package by.bsuir.repository.api.core;

import by.bsuir.entity.company.Company;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends AbstractRepository<Company> {

    Optional<Company> findByName(String name);

}
