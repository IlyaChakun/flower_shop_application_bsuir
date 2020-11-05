package by.bsuir.security.core.filter;

import by.bsuir.security.core.SecurityContextConstants;
import by.bsuir.security.core.TokenProvider;
import by.bsuir.security.exception.InvalidTokenTypeException;
import by.bsuir.security.service.api.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * this filter provides token validation
 */
@AllArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.info("TokenAuthenticationFilter works");

        final String grantType = getGrantType(request);

        logger.info("Grant type: " + grantType);

        if (!grantType.equals(SecurityContextConstants.GRANT_TYPE_ANON_ACTION.getValue())) {//if anon action then miss
            doTokenValidation(request, grantType);
        }

        filterChain.doFilter(request, response);
    }

    private void doTokenValidation(final HttpServletRequest request,
                                   final String grantType) {

        logger.info("validation token works");
        logger.info("grantType=" + grantType);

        final String tokenType = getTokenType(request);
        final String authorizationAccessToken = getAuthorizationAccessToken(request);
        final String authorizationRefreshToken = getAuthorizationRefreshToken(request);


        if (!tokenType.equals(SecurityContextConstants.VALID_TOKEN_TYPE.getValue())) {
            logger.info("The token type is invalid! Please, use " + SecurityContextConstants.VALID_TOKEN_TYPE.getValue()
                    + ",  actual token type= " + tokenType);
            throw new InvalidTokenTypeException("The token type is invalid! Please, use bearer!");
        }

        if (grantType.equals(SecurityContextConstants.GRANT_TYPE_REFRESH_TOKEN.getValue())) {//if refresh action
            logger.info("validation refresh token");
            tokenProvider.validateRefreshTokenOrThrowException(authorizationRefreshToken);
        } else {
            if (StringUtils.hasText(authorizationAccessToken) &&
                    StringUtils.hasText(authorizationRefreshToken)) {//firstly checking string valid
                //
                tokenProvider.validateAccessTokenOrThrowException(authorizationAccessToken);
                tokenProvider.validateRefreshTokenOrThrowException(authorizationRefreshToken);
                //
                setAuthenticationToContext(request, authorizationAccessToken);
            }
        }
    }

    private void setAuthenticationToContext(HttpServletRequest request,
                                            String authorizationAccessToken) {
        final Long userId = tokenProvider.getUserIdFromToken(authorizationAccessToken);

        UserDetails userDetails = userDetailsService.loadUserById(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null,//credentials
                        userDetails.getAuthorities());
        authentication.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private String getAuthorizationAccessToken(HttpServletRequest request) {
        return request.getHeader(SecurityContextConstants.AUTHORIZATION_ACCESS_TOKEN.getValue());
    }

    private String getAuthorizationRefreshToken(HttpServletRequest request) {
        return request.getHeader(SecurityContextConstants.AUTHORIZATION_REFRESH_TOKEN.getValue());
    }

    private String getGrantType(HttpServletRequest request) {
        final String grantType = request.getHeader(SecurityContextConstants.GRANT_TYPE.getValue());
        return Objects.isNull(grantType) ? "" : grantType;
    }

    private String getTokenType(HttpServletRequest request) {
        final String tokenType = request.getHeader(SecurityContextConstants.TOKEN_TYPE.getValue());
        return Objects.isNull(tokenType) ? "" : tokenType;
    }

}
