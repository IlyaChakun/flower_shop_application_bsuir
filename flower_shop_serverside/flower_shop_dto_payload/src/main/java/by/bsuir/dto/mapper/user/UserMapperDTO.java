package by.bsuir.dto.mapper.user;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO extends AbstractMapperDTO<User, UserDTO> {

    @Autowired
    public UserMapperDTO() {
        super(User.class, UserDTO.class);
    }
}