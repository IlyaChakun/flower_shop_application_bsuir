package by.bsuir.repository.api;

import by.bsuir.entity.company.Company;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends AbstractRepository<Company> {

    Optional<Company> findByName(String name);

    Company getByName(String name);

    Optional<Company> findByShopAdminId(Long shopAdminId);

}
