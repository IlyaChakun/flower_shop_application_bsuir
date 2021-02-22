package by.bsuir.config;

import by.bsuir.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore = new InMemoryTokenStore();
    private final String NOOP_PASSWORD_ENCODE = "{noop}";

    private static final String DEF_PASS = "pass";
    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // TODO persist clients details

        // @formatter:off
        clients.inMemory()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("ui")
                .and()
                /** florist-service */
                .withClient("florist-service")
                .secret(DEF_PASS)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .and()
                /** account-service */
                .withClient("account-service")
                .secret(DEF_PASS)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server");
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());//TODO
    }

}