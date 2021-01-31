package by.bsuir.security.oauth2;


import by.bsuir.dto.model.common.ImageDTO;

import by.bsuir.dto.user.UserDTO;
import by.bsuir.dto.user.UserRoleDTO;
import by.bsuir.entity.SupportedAuthProvider;
import by.bsuir.entity.UserRoles;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.security.exception.OAuth2AuthenticationProcessingException;
import by.bsuir.security.oauth2.user.OAuth2UserInfo;
import by.bsuir.security.oauth2.user.OAuth2UserInfoFactory;
import by.bsuir.security.service.api.UserSecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserSecurityService userService;


    @Override
    @Transactional
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw new OAuth2AuthenticationProcessingException(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage());
        }
    }


    private OAuth2User processOAuth2User(final OAuth2UserRequest oAuth2UserRequest,
                                         final OAuth2User oAuth2User) {
        final OAuth2UserInfo oAuth2UserInfo =
                OAuth2UserInfoFactory
                        .getOAuth2UserInfo(
                                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                                oAuth2User.getAttributes());

        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        final Optional<UserDTO> userOptional = userService.findByEmail(oAuth2UserInfo.getEmail());

        UserDTO user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            resolveProvider(oAuth2UserRequest, user);
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private void resolveProvider(final OAuth2UserRequest oAuth2UserRequest,
                                 final UserDTO user) {
        if (!user.getProvider().equals(
                SupportedAuthProvider.valueOf(
                        oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
            throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                    user.getProvider() + " account. Please use your " + user.getProvider() +
                    " account to login.");
        }
    }


    private UserDTO registerNewUser(final OAuth2UserRequest oAuth2UserRequest,
                                    final OAuth2UserInfo oAuth2UserInfo) {
        UserDTO user = new UserDTO();
        user.setProvider(SupportedAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImage(new ImageDTO(oAuth2UserInfo.getImageUrl()));
        user.setUserRole(new UserRoleDTO(UserRoles.ROLE_CLIENT));

        return userService.saveClient(user);
    }

    private UserDTO updateExistingUser(final UserDTO existingUser,
                                       final OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        //existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());

        return userService.saveClient(existingUser);
    }

}
