package by.bsuir.flowershop.security.service.impl;



import by.bsuir.flowershop.dto.user.UserDTO;
import by.bsuir.flowershop.security.core.UserPrincipal;
import by.bsuir.flowershop.security.service.api.CustomUserDetailsService;
import by.bsuir.flowershop.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserSecurityService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final UserDTO user =
                userService.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found with email : " + email)
                        );

        return UserPrincipal.create(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserById(Long id) {
        UserDTO user = userService.getOne(id);
        return UserPrincipal.create(user);
    }

}