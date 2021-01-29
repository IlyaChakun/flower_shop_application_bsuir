package by.bsuir.flowershop.security.service.api;

import by.bsuir.flowershop.dto.user.UserDTO;
import by.bsuir.flowershop.security.dto.signup.UserSignUpRequest;

import java.util.Optional;

public interface UserSecurityService {

    UserDTO registerUser(UserSignUpRequest signUpRequest);

    boolean existsByEmail(String email);

    Optional<UserDTO> findByEmail(String email);

    UserDTO getOne(Long id);

    UserDTO findById(Long id);

    void confirmUserAccount(final String confirmationToken);

    UserDTO saveClient(UserDTO userDTO);//means aouth2

    UserDTO update(UserDTO userDTO);
}
