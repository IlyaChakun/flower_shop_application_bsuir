package by.bsuir.flowershop.service;


import by.bsuir.flowershop.dto.user.UserDTO;

public interface UserService extends AbstractUserService<UserDTO> {

    UserDTO update(UserDTO user, Long userId);

}
