package by.bsuir.security.service.impl;

import by.bsuir.dto.mapper.user.UserMapperDTO;
import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.entity.user.*;
import by.bsuir.entity.user.token.UserConfirmationToken;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.user.ClientRepository;
import by.bsuir.repository.api.user.RoleRepository;
import by.bsuir.repository.api.user.UserBaseRepository;
import by.bsuir.repository.api.user.UserConfirmationTokenRepository;
import by.bsuir.security.dto.signup.ClientSignUpRequest;
import by.bsuir.security.exception.ConfirmationTokeBrokenLinkException;
import by.bsuir.security.exception.DuplicateEmailException;
import by.bsuir.security.mail.UserSecurityMailService;
import by.bsuir.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService {

    private final UserBaseRepository userRepository;
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConfirmationTokenRepository userConfirmationTokenRepository;
    private final UserSecurityMailService userSecurityMailService;

    private final UserMapperDTO userMapper;

    @Override
    @Transactional
    public AbstractUserDTO registerClient(ClientSignUpRequest signUpRequest) {
        Client client = getClient(signUpRequest);
        Client savedClient = clientRepository.save(client);
        //
        UserConfirmationToken confirmationToken = new UserConfirmationToken(client);
        userConfirmationTokenRepository.save(confirmationToken);
        doSendConfirmationMail(client, confirmationToken);
        return userMapper.toDto(savedClient);
    }

    private void doSendConfirmationMail(Client client, UserConfirmationToken confirmationToken) {
        //
        userSecurityMailService.sendConfirmAccountEmail(client.getEmail(), confirmationToken.getConfirmationToken());
        //
    }

    private Client getClient(ClientSignUpRequest signUpRequest) {
        Client client = new Client();
        client.setName(signUpRequest.getName());
        client.setEmail(signUpRequest.getEmail());
        client.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        client.setProvider(SupportedAuthProvider.local);
        return client;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<AbstractUserDTO> findByEmail(String email) {
        Optional<AbstractUser> userOptional = userRepository.findByEmail(email);
        return userOptional.map(userMapper::toDto);
    }

    @Override
    public AbstractUserDTO getOne(Long id) {
        return userMapper.toDto(userRepository.getOne(id));
    }

    @Override
    @Transactional
    public void confirmUserAccount(final String confirmationToken) {

        Optional<UserConfirmationToken> token = userConfirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token.isPresent()) {
            AbstractUser user = userRepository.getByEmail(token.get().getUser().getEmail());
            user.setMailConfirmed(true);
            userRepository.save(user);
        } else {
            throw new ConfirmationTokeBrokenLinkException("Link is broken!");
        }
    }

    @Override
    @Transactional
    public AbstractUserDTO save(AbstractUserDTO abstractUserDTO) {

        clientRepository.findByEmail(abstractUserDTO.getEmail())
                .ifPresent(value -> {
                            throw new DuplicateEmailException("Email address " + value.getEmail() + " already in use.");
                        }
                );

        Client userToSave = new Client();
        userToSave.setName(abstractUserDTO.getName());
        userToSave.setEmail(abstractUserDTO.getEmail());
//        userToSave.setPassword(passwordEncoder.encode(abstractUserDTO.getPassword()));
        userToSave.setProvider(SupportedAuthProvider.local);

        Role role = roleRepository.findByName(UserRoles.ROLE_CLIENT.getRoleName());
        userToSave.setRoles(Collections.singleton(role));

        Client savedClient = clientRepository.save(userToSave);
        //
        UserConfirmationToken confirmationToken = new UserConfirmationToken(savedClient);
        userConfirmationTokenRepository.save(confirmationToken);
        doSendConfirmationMail(savedClient, confirmationToken);
        //
        return userMapper.toDto(savedClient);
    }

    @Override
    @Transactional
    public AbstractUserDTO update(AbstractUserDTO abstractUserDTO) {

        Client prevUser = clientRepository.findById(abstractUserDTO.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client with id = " + abstractUserDTO.getId() + " not found!")
                );


        prevUser.setName(abstractUserDTO.getName());
        prevUser.setEmail(abstractUserDTO.getEmail());
        prevUser.setPassword(passwordEncoder.encode(abstractUserDTO.getPassword()));
        prevUser.setProvider(SupportedAuthProvider.local);


        return userMapper.toDto(prevUser);
    }

}
