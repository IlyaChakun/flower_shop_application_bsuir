package by.bsuir.security.service.impl;


import by.bsuir.dto.model.user.AbstractUserDTO;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.security.service.api.CustomUserDetailsService;
import by.bsuir.security.service.api.UserSecurityService;
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

        final AbstractUserDTO user =
                userService.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found with email : " + email)
                        );

        return UserPrincipal.create(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserById(Long id) {
        AbstractUserDTO user = userService.getOne(id);
        return UserPrincipal.create(user);
    }

}