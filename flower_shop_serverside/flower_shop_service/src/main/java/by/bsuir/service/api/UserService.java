package by.bsuir.service.api;

import by.bsuir.dto.model.user.UserDTO;

public interface UserService extends AbstractUserService<UserDTO> {

    UserDTO update(UserDTO user, Long userId);

}
