package by.bsuir.repository.api.user;

import by.bsuir.entity.user.UserRole;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<UserRole> {

    UserRole findByName(String name);

}
