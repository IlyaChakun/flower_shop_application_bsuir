package by.bsuir.repository.api;


import by.bsuir.repository.api.core.AbstractRepository;
import by.bsuir.entity.token.UserConfirmationToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserConfirmationTokenRepository extends AbstractRepository<UserConfirmationToken> {

    Optional<UserConfirmationToken> findByConfirmationToken(String confirmationToken);

}
