package by.bsuir.service.impl;

import by.bsuir.dto.mapper.user.ClientMapperDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.entity.user.Client;
import by.bsuir.repository.api.user.ClientRepository;
import by.bsuir.service.api.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapperDTO clientMapper;

    @Override
    public Optional<ClientDTO> findByEmail(String email) {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        return clientOptional.map(clientMapper::toDto);
    }

    @Override
    public ClientDTO getByEmail(String email) {
        return clientMapper.toDto(clientRepository.getByEmail(email));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
