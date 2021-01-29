package by.bsuir.flowershop.dto.mapper;


import by.bsuir.flowershop.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.flowershop.dto.user.UserDTO;
import by.bsuir.flowershop.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO extends AbstractMapperDTO<User, UserDTO> {

    @Autowired
    public UserMapperDTO() {
        super(User.class, UserDTO.class);
    }
}