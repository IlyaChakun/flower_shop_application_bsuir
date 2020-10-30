package by.bsuir.security.service.impl;

import by.bsuir.entity.user.AbstractUser;
import by.bsuir.entity.user.SupportedAuthProvider;
import by.bsuir.repository.api.UserRepository;
import by.bsuir.security.dto.signup.SignUpRequest;
import by.bsuir.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AbstractUser register(SignUpRequest signUpRequest) {
        AbstractUser abstractUser = new AbstractUser();
        abstractUser.setName(signUpRequest.getName());
        abstractUser.setEmail(signUpRequest.getEmail());
        abstractUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        abstractUser.setProvider(SupportedAuthProvider.local);


        return this.save(abstractUser);
    }

    @Override
    public AbstractUser save(AbstractUser abstractUser) {
        return userRepository.save(abstractUser);
    }

    @Override
    public AbstractUser update(AbstractUser abstractUser) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<AbstractUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AbstractUser getOne(Long id) {
        return userRepository.getOne(id);
    }
}
