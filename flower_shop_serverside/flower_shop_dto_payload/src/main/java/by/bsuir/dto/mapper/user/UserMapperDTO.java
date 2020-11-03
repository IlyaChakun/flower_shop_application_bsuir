package by.bsuir.dto.mapper.user;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.entity.user.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO extends AbstractMapperDTO<AbstractUser, AbstractUserDTO> {

    @Autowired
    public UserMapperDTO() {
        super(AbstractUser.class, AbstractUserDTO.class);
    }
}