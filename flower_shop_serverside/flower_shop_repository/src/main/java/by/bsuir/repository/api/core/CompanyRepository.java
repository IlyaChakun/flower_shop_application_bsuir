package by.bsuir.repository.api.core;

import by.bsuir.entity.company.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends AbstractRepository<Company> {

    Company findByName(String name);
}
