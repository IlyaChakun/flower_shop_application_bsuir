package by.bsuir.dto.mapper.user;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.user.RoleDTO;
import by.bsuir.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperDTO extends AbstractMapperDTO<Role, RoleDTO> {

    @Autowired
    public RoleMapperDTO() {
        super(Role.class, RoleDTO.class);
    }
}