package by.bsuir.flowershop.repository.api.user;

import by.bsuir.flowershop.entity.user.token.UserConfirmationToken;
import by.bsuir.flowershop.repository.api.core.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserConfirmationTokenRepository extends AbstractRepository<UserConfirmationToken> {

    Optional<UserConfirmationToken> findByConfirmationToken(String confirmationToken);

}
