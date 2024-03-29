package by.bsuir.security.service.impl;

import by.bsuir.dto.model.common.ImageDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.dto.model.user.signup.UserSignUpRequest;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.user.SupportedAuthProvider;
import by.bsuir.entity.user.User;
import by.bsuir.entity.user.UserRole;
import by.bsuir.repository.api.user.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public final class UserConverter {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    User getUser(UserSignUpRequest signUpRequest) {
        String imageUrl = getImageUrl(signUpRequest.getImage());
        return getUser(
                signUpRequest.getRoleType(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                SupportedAuthProvider.local,
                imageUrl,
                signUpRequest.getPhoneNumber()
        );
    }

    private String getImageUrl(ImageDTO image) {
        return Objects.nonNull(image) ? image.getImageUrl() : "";
    }

    User getUser(UserDTO userDTO) {
        String imageUrl = getImageUrl(userDTO.getImage());
        return getUser(
                userDTO.getUserRole().getName(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getProvider(),
                imageUrl,
                userDTO.getPhoneNumber()
        );
    }

    private User getUser(String roleName,
                         String name,
                         String email,
                         String password,
                         SupportedAuthProvider provider,
                         String image,
                         String phoneNumber) {
        User user = new User();
        UserRole userRole = roleRepository.findByName(roleName);
        user.setName(name);
        user.setEmail(email);
        if (Objects.nonNull(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setProvider(provider);
        user.setUserRole(userRole);
        user.setImage(new Image(image));
        user.setPhoneNumber(phoneNumber);

        return user;
    }

    void resetClientField(User user, UserDTO userDTO) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setProvider(SupportedAuthProvider.local);
    }

}
