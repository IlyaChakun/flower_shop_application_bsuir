package by.bsuir.repository.api.user;

import by.bsuir.entity.user.User;
import by.bsuir.repository.api.core.AbstractRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    Boolean existsByEmail(String email);

    Page<User> findAllByUserRoleName(Pageable pageable, String userRoleName);

}
