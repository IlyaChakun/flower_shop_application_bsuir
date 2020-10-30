package by.bsuir.security.service.api;

import by.bsuir.entity.user.AbstractUser;
import by.bsuir.security.dto.signup.SignUpRequest;

import java.util.Optional;

public interface UserSecurityService {

    AbstractUser register(SignUpRequest signUpRequest);

    AbstractUser save(AbstractUser abstractUser);

    AbstractUser update(AbstractUser abstractUser);

    boolean existsByEmail(String email);

    Optional<AbstractUser> findByEmail(String email);

    AbstractUser getOne(Long id);

}
