package by.bsuir.flowershop.service;


import by.bsuir.flowershop.dto.user.UserDTO;

public interface AbstractUserService<T extends UserDTO> {

    T findByEmail(String email);

    T getByEmail(String email);

    Boolean existsByEmail(String email);

}
