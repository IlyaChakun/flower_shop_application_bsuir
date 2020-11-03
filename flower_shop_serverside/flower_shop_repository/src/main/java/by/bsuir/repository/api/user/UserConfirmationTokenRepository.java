package by.bsuir.repository.api.user;

import by.bsuir.entity.user.token.UserConfirmationToken;
import by.bsuir.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserConfirmationTokenRepository extends AbstractRepository<UserConfirmationToken> {

    Optional<UserConfirmationToken> findByConfirmationToken(String confirmationToken);

}
