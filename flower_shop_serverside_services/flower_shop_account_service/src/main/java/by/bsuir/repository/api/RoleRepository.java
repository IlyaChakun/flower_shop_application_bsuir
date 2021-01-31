package by.bsuir.repository.api;


import by.bsuir.repository.api.core.AbstractRepository;
import by.bsuir.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<UserRole> {

    UserRole findByName(String name);

}
