package by.bsuir.service;

import by.bsuir.entity.User;
import by.bsuir.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository repository;

    @Override
    public void create(User user) {

        Optional<User> existing = repository.findById(user.getId());
        existing.ifPresent(it -> {
                    throw new IllegalArgumentException("user already exists: " + it.getId());
                }
        );

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("new user has been created: {}", user.getEmail());
    }


}
