package by.bsuir.repository.api.company;


import by.bsuir.entity.company.Company;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends AbstractRepository<Company> {

    Company findFirstBy();
}
