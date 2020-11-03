package by.bsuir.security.service.api;

import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.security.dto.signup.ClientSignUpRequest;

import java.util.Optional;

public interface UserSecurityService {

    AbstractUserDTO registerClient(ClientSignUpRequest signUpRequest);

    boolean existsByEmail(String email);

    Optional<AbstractUserDTO> findByEmail(String email);

    AbstractUserDTO getOne(Long id);

    void confirmUserAccount(final String confirmationToken);

    AbstractUserDTO save(AbstractUserDTO abstractUserDTO);//means aouth2

    AbstractUserDTO update(AbstractUserDTO abstractUserDTO);
}
