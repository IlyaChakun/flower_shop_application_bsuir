package by.bsuir.flowershop.repository.api.user;

import by.bsuir.flowershop.entity.user.UserRole;
import by.bsuir.flowershop.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AbstractRepository<UserRole> {

    UserRole findByName(String name);

}
