package by.bsuir.dto.mapper.user;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.entity.user.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperDTO extends AbstractMapperDTO<Client, ClientDTO> {

    @Autowired
    public ClientMapperDTO() {
        super(Client.class, ClientDTO.class);
    }
}