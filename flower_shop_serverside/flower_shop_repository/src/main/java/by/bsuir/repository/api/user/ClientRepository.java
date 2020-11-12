package by.bsuir.repository.api.user;

import by.bsuir.entity.user.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends AbstractUserBaseRepository<Client> {

    Optional<Client> findByUniqueId(String uniqueId);
}
