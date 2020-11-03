package by.bsuir.security.core.impl;


import by.bsuir.security.config.ApplicationTokenSecurityProperties;
import by.bsuir.security.core.TokenProvider;
import by.bsuir.security.core.UserPrincipal;
import by.bsuir.security.dto.AuthTokenResponse;
import by.bsuir.security.exception.AccessTokenException;
import by.bsuir.security.exception.RefreshTokenException;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
@AllArgsConstructor
public class TokenProviderImpl implements TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final ApplicationTokenSecurityProperties applicationTokenSecurityProperties;

    @Override
    public AuthTokenResponse updateAuthToken(final HttpServletRequest request) {

        final String refreshToken = request.getHeader("Authorization-Refresh-Token");
        //
        this.validateRefreshTokenOrThrowException(refreshToken);
        //
        final Long userId = getUserIdFromRefreshToken(refreshToken);

        final String accessToken =
                this.getToken(userId,
                        applicationTokenSecurityProperties.getAuth().getAccessTokenExpirationMsec(),
                        applicationTokenSecurityProperties.getAuth().getAccessTokenSecret());

        final String newRefreshToken =
                this.getToken(userId,
                        applicationTokenSecurityProperties.getAuth().getRefreshTokenExpirationMsec(),
                        applicationTokenSecurityProperties.getAuth().getRefreshTokenSecret());

        logger.info("TOKENS AFTER UPDATE");
        logger.info("ACCESS TOKEN=   " + accessToken);
        logger.info("NEW REFRESH TOKEN=   " + newRefreshToken);

        return this.getAuthTokenResponse(accessToken, newRefreshToken);

    }

    @Override
    public AuthTokenResponse buildAuthTokenResponse(final Authentication authentication) {
        final String accessToken = createAccessToken(authentication);
        final String refreshToken = createRefreshToken(authentication);

        logger.info("ACCESS TOKEN=   " + accessToken);
        logger.info("REFRESH TOKEN=   " + refreshToken);

        return getAuthTokenResponse(accessToken, refreshToken);
    }


    private AuthTokenResponse getAuthTokenResponse(final String accessToken,
                                                   final String refreshToken) {

        final Date expiryDate = getRefreshTokenExpiryDate();

        return
                new AuthTokenResponse(accessToken, refreshToken, expiryDate.getTime());
    }

    private Date getRefreshTokenExpiryDate() {
        final Date now = new Date();
        return
                this.doGetExpiryDate(
                        now,
                        applicationTokenSecurityProperties.getAuth().getRefreshTokenExpirationMsec()
                );
    }

    private String createAccessToken(final Authentication authentication) {
        final UserPrincipal userPrincipal = getUserPrincipal(authentication);

        return
                getToken(userPrincipal.getId(),
                        applicationTokenSecurityProperties.getAuth().getAccessTokenExpirationMsec(),
                        applicationTokenSecurityProperties.getAuth().getAccessTokenSecret());
    }

    private String getToken(final Long userId,
                            final long tokenExpirationMsec,
                            final String tokenSecret) {

        logger.info("userId= " + userId + ", tokenExpirationMsec=" + tokenExpirationMsec + ", tokenSecret=" + tokenSecret);

        final Date now = new Date();
        final Date expiryDate = doGetExpiryDate(now, tokenExpirationMsec);

        logger.info("expiryDate=" + expiryDate);

        return
                Jwts.builder()
                        .setSubject(Long.toString(userId))
                        .setIssuedAt(now)
                        .setExpiration(expiryDate)
                        .signWith(SignatureAlgorithm.HS512, tokenSecret)
                        .compact();
    }

    private Date doGetExpiryDate(final Date now,
                                 final long tokenExpirationMsec) {
        return
                new Date(
                        now.getTime() + tokenExpirationMsec
                );
    }

    private UserPrincipal getUserPrincipal(final Authentication authentication) {
        return (UserPrincipal) authentication.getPrincipal();
    }


    private String createRefreshToken(final Authentication authentication) {
        UserPrincipal userPrincipal = getUserPrincipal(authentication);

        return
                getToken(userPrincipal.getId(),
                        applicationTokenSecurityProperties.getAuth().getRefreshTokenExpirationMsec(),
                        applicationTokenSecurityProperties.getAuth().getRefreshTokenSecret());
    }

    @Override
    public Long getUserIdFromToken(final String token) {
        return doGetUserIdFromToken(token, applicationTokenSecurityProperties.getAuth().getAccessTokenSecret());
    }

    @Override
    public void validateAccessTokenOrThrowException(final String accessToken) {
        try {
            Jwts.parser()
                    .setSigningKey(applicationTokenSecurityProperties.getAuth().getAccessTokenSecret())
                    .parseClaimsJws(accessToken);
            logger.info("access token valid!");
        } catch (SignatureException ex) {
            logger.error("Invalid access JWT signature");
            throw new AccessTokenException("Invalid access JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid access JWT token");
            throw new AccessTokenException("Invalid access JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired access JWT token");
            throw new AccessTokenException("Expired access JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported access JWT token");
            throw new AccessTokenException("Unsupported access JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("access JWT claims string is empty.");
            throw new AccessTokenException("access JWT claims string is empty");
        }
    }


    @Override
    public void validateRefreshTokenOrThrowException(final String refreshToken) {
        try {
            Jwts.parser()
                    .setSigningKey(applicationTokenSecurityProperties.getAuth().getRefreshTokenSecret())
                    .parseClaimsJws(refreshToken);
            logger.info("refresh token valid!");
        } catch (SignatureException ex) {
            logger.error("Invalid refresh JWT signature");
            throw new RefreshTokenException("Invalid refresh JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid refresh JWT token");
            throw new RefreshTokenException("Invalid refresh JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired refresh JWT token");
            throw new RefreshTokenException("Expired refresh  JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported refresh JWT token");
            throw new RefreshTokenException("Unsupported refresh JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("refresh JWT claims string is empty.");
            throw new RefreshTokenException("refresh JWT claims string is empty");
        }
    }

    private Long getUserIdFromRefreshToken(final String token) {
        return doGetUserIdFromToken(token, applicationTokenSecurityProperties.getAuth().getRefreshTokenSecret());
    }

    private Long doGetUserIdFromToken(final String token,
                                      final String refreshTokenSecret) {
        final Claims claims = Jwts.parser()
                .setSigningKey(refreshTokenSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

}
