package by.bsuir.service;


import by.bsuir.dto.user.UserDTO;

public interface UserService extends AbstractUserService<UserDTO> {

    UserDTO update(UserDTO user, Long userId);

}
