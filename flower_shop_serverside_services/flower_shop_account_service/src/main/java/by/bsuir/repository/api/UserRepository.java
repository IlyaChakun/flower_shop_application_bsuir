package by.bsuir.repository.api;


import by.bsuir.repository.api.core.AbstractRepository;
import by.bsuir.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    Boolean existsByEmail(String email);

}
