package by.bsuir.service;


import by.bsuir.dto.mapper.UserMapperDTO;
import by.bsuir.dto.user.UserDTO;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.User;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.payload.ServiceException;
import by.bsuir.repository.api.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapperDTO userMapper;

    @Override
    public UserDTO findByEmail(String email) {
        User client = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Client with such a email " + email + " is absent in our base"));

        return userMapper.toDto(client);
    }

    @Override
    public UserDTO getByEmail(String email) {
        return userMapper.toDto(userRepository.getByEmail(email));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Client with userId={} doesn't exist!", userId);
                    throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                            "client_not_found",
                            "Client with uniqueId=" + userId + " doesn't exist!");
                });

        user.setImage(new Image(userDTO.getImage().getImageUrl()));
        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return userMapper.toDto(user);
    }

}
