package by.bsuir.service.api;

import by.bsuir.dto.model.user.AbstractUserDTO;

import java.util.Optional;

public interface AbstractUserService<T extends AbstractUserDTO> {

    Optional<T> findByEmail(String email);

    T getByEmail(String email);

    Boolean existsByEmail(String email);

}
