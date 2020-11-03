package by.bsuir.repository.api.user;

import by.bsuir.entity.user.Role;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {

    Role findByName(String name);

}
