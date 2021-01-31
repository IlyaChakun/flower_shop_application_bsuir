package by.bsuir.dto.mapper;


import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.user.UserDTO;
import by.bsuir.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO extends AbstractMapperDTO<User, UserDTO> {

    @Autowired
    public UserMapperDTO() {
        super(User.class, UserDTO.class);
    }
}