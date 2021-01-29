package by.bsuir.service.api;

import by.bsuir.dto.model.user.UserDTO;

public interface AbstractUserService<T extends UserDTO> {

    T findByEmail(String email);

    T getByEmail(String email);

    Boolean existsByEmail(String email);

}
