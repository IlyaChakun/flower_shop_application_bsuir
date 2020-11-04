package by.bsuir.security.service.impl;

import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.entity.user.Client;
import by.bsuir.entity.user.Role;
import by.bsuir.entity.user.SupportedAuthProvider;
import by.bsuir.entity.user.UserRoles;
import by.bsuir.repository.api.user.RoleRepository;
import by.bsuir.security.dto.signup.ClientSignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@AllArgsConstructor
@Component
public final class ClientConverter {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    Client getClient(ClientSignUpRequest signUpRequest) {
        Client client = new Client();
        client.setName(signUpRequest.getName());
        client.setEmail(signUpRequest.getEmail());
        client.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        client.setProvider(SupportedAuthProvider.local);

        Role role = roleRepository.findByName(UserRoles.ROLE_CLIENT.getRoleName());
        client.setRoles(Collections.singleton(role));

        return client;
    }


    void resetClientField(Client client, AbstractUserDTO abstractUserDTO) {
        client.setName(abstractUserDTO.getName());
        client.setEmail(abstractUserDTO.getEmail());
        client.setProvider(SupportedAuthProvider.local);
    }

}
