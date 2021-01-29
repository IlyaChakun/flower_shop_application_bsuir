package by.bsuir.flowershop.repository.api.company;


import by.bsuir.flowershop.entity.company.Company;
import by.bsuir.flowershop.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends AbstractRepository<Company> {

    Company findFirstBy();
}
