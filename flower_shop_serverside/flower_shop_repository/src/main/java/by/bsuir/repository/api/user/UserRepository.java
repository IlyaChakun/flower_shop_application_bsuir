package by.bsuir.repository.api.user;

import by.bsuir.entity.user.User;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    Boolean existsByEmail(String email);

}
