package by.bsuir.repository.api;

import by.bsuir.entity.user.AbstractUser;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<AbstractUser> {

    Boolean existsByEmail(String email);

    Optional<AbstractUser> findByEmail(String email);

}
