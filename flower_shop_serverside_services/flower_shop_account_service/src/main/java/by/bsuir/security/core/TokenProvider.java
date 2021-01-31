package by.bsuir.security.core;

import by.bsuir.security.dto.AuthTokenResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>This service provides logic for JWT token with Bearer type.</p>
 * <p>
 * With this service you are able to build token and then, when access token is expired,
 * token can be updated by updateAuthToken method;
 */
public interface TokenProvider {

    /**
     * Method provides updating access and refresh tokens if refresh token is valid and not expired
     *
     * @param request From request we are able to get all reqired params
     * @return @see {@link AuthTokenResponse}
     */
    AuthTokenResponse updateAuthToken(final HttpServletRequest request);

    /**
     * Method returns new token response with Access token, Refresh token and expire time for refresh token
     *
     * @param authentication
     * @return @see {@link AuthTokenResponse}
     */
    AuthTokenResponse buildAuthTokenResponse(final Authentication authentication);

    /**
     * Method returns userId by access or refresh token
     *
     * @param token Token which must contains userId
     * @return {@code Long userId}
     */
    Long getUserIdFromToken(final String token);

    /**
     * This method can throw exception with information about problem.
     * It validates all kind of problems that can arise in token.
     *
     * @param accessToken Access Token for validation
     */
    void validateAccessTokenOrThrowException(final String accessToken);

    /**
     * This method can throw exception with information about problem.
     * It validates all kind of problems that can arise in token.
     *
     * @param refreshToken Refresh Token for validation
     */
    void validateRefreshTokenOrThrowException(final String refreshToken);

}
