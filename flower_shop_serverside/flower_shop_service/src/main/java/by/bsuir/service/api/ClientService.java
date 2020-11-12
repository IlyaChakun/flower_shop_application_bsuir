package by.bsuir.service.api;

import by.bsuir.dto.model.user.ClientDTO;

public interface ClientService extends AbstractUserService<ClientDTO> {

    ClientDTO update(ClientDTO clientDTO, String uniqueId);

}
