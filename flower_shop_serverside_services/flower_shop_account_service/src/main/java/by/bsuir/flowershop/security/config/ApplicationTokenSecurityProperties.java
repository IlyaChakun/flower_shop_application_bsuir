package by.bsuir.flowershop.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "security-token-app")
@Getter
public class ApplicationTokenSecurityProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    @Getter
    @Setter
    /**
     * This class is holding meta information about both tokens (refresh and access)
     */
    public static class Auth {
        private String accessTokenSecret;
        private long accessTokenExpirationMsec;

        private String refreshTokenSecret;
        private long refreshTokenExpirationMsec;

    }

    @Getter
    @Setter
    /**
     * This class is holding redirect urls for oAuth2
     */
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

    }

}