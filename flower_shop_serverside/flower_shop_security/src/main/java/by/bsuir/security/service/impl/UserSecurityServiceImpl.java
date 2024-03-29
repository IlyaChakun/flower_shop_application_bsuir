package by.bsuir.security.service.impl;

import by.bsuir.dto.mapper.user.UserMapperDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.dto.model.user.signup.UserSignUpRequest;
import by.bsuir.entity.user.User;
import by.bsuir.entity.user.token.UserConfirmationToken;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.user.UserConfirmationTokenRepository;
import by.bsuir.repository.api.user.UserRepository;

import by.bsuir.security.exception.ConfirmationTokeBrokenLinkException;
import by.bsuir.security.exception.DuplicateEmailException;
import by.bsuir.security.mail.UserSecurityMailService;
import by.bsuir.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService {

    private static final Logger log = LoggerFactory.getLogger(UserSecurityServiceImpl.class);

    private final UserRepository userRepository;
    private final UserConfirmationTokenRepository userConfirmationTokenRepository;
    private final UserSecurityMailService userSecurityMailService;

    private final UserMapperDTO userMapper;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public UserDTO registerUser(UserSignUpRequest signUpRequest) {

        checkUserEmailAvailability(signUpRequest.getEmail());

        User client = userRepository.save(userConverter.getUser(signUpRequest));
        createAndSendConfirmationToken(client);
        return this.userMapper.toDto(client);

    }

    private void doSendConfirmationMail(User user, UserConfirmationToken confirmationToken) {
        //
        userSecurityMailService.sendConfirmAccountEmail(user.getEmail(), confirmationToken.getConfirmationToken());
        //
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(userMapper::toDto);
    }

    @Override
    public UserDTO getOne(Long id) {
        return userMapper.toDto(userRepository.getOne(id));
    }

    @Override
    public UserDTO getOneByMail(String email) {
        return userMapper.toDto(userRepository.getByEmail(email));
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id=" + id + " not found.")));
    }

    @Override
    @Transactional
    public void confirmUserAccount(final String confirmationToken) {

        Optional<UserConfirmationToken> token = userConfirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token.isPresent()) {
            User user = userRepository.getByEmail(token.get().getUser().getEmail());
            user.setMailConfirmed(true);
            userRepository.save(user);
        } else {
            throw new ConfirmationTokeBrokenLinkException("Link is broken!");
        }
    }

    @Override
    @Transactional
    public UserDTO saveClient(UserDTO userDTO) {
        return doSaveUser(userDTO);
    }


    private UserDTO doSaveUser(UserDTO userDTO) {
        checkUserEmailAvailability(userDTO.getEmail());

        User userToSave = userConverter.getUser(userDTO);

        User savedClient = userRepository.save(userToSave);
        //
        createAndSendConfirmationToken(savedClient);
        //
        return userMapper.toDto(savedClient);
    }

    private void createAndSendConfirmationToken(User savedUser) {
        UserConfirmationToken confirmationToken = new UserConfirmationToken(savedUser);
        userConfirmationTokenRepository.save(confirmationToken);
        doSendConfirmationMail(savedUser, confirmationToken);
    }

    private void checkUserEmailAvailability(String email) {
        log.info("find users by email {}", email);
        userRepository.findByEmail(email).ifPresent(value -> System.out.println(value.getEmail()));

        userRepository.findByEmail(email)
                .ifPresent(value -> {
                            throw new DuplicateEmailException("Email address " + value.getEmail() + " already in use.");
                        }
                );
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO) {

        User prevUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client with id = " + userDTO.getId() + " not found!")
                );

        userConverter.resetClientField(prevUser, userDTO);

        return userMapper.toDto(prevUser);
    }

}
