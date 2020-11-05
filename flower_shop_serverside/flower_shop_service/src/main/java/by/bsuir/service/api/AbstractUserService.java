package by.bsuir.service.api;

import by.bsuir.dto.model.user.AbstractUserDTO;

public interface AbstractUserService<T extends AbstractUserDTO> {

    T findByEmail(String email);

    T getByEmail(String email);

    Boolean existsByEmail(String email);

}
