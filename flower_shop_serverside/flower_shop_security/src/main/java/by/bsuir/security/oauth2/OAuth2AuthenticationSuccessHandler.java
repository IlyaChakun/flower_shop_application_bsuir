package by.bsuir.security.oauth2;


import by.bsuir.security.config.ApplicationTokenSecurityProperties;
import by.bsuir.security.core.TokenProvider;
import by.bsuir.security.dto.AuthTokenResponse;
import by.bsuir.security.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;


@Component
@AllArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final ApplicationTokenSecurityProperties applicationTokenSecurityProperties;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);

        getRedirectStrategy()
                .sendRedirect(request, response, targetUrl);
    }


    protected String determineTargetUrl(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) {

        Optional<String> redirectUri =
                CookieUtils.getCookie(request, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME)
                        .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        AuthTokenResponse authTokenResponse = tokenProvider.buildAuthTokenResponse(authentication);

        return
                UriComponentsBuilder.fromUriString(targetUrl)
                        .queryParam("accessToken", authTokenResponse.getAccessToken())
                        .queryParam("refreshToken", authTokenResponse.getRefreshToken())
                        .queryParam("expiresIn", authTokenResponse.getExpiresIn())
                        .build()
                        .toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request,
                                                 HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        final URI clientRedirectUri = URI.create(uri);
        return applicationTokenSecurityProperties.getOauth2()
                .getAuthorizedRedirectUris()
                .stream()
                .map(URI::create)
                .anyMatch(authorizedURI ->
                        authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                                && authorizedURI.getPort() == clientRedirectUri.getPort());
    }
}
