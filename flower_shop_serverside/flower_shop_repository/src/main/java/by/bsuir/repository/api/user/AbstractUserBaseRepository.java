package by.bsuir.repository.api.user;


import by.bsuir.entity.user.AbstractUser;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AbstractUserBaseRepository<T extends AbstractUser> extends AbstractRepository<T> {

    Optional<T> findByEmail(String email);

    T getByEmail(String email);

    Boolean existsByEmail(String email);

}
