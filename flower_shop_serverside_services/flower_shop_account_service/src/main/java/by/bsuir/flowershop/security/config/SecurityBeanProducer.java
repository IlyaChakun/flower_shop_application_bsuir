package by.bsuir.flowershop.security.config;

import by.bsuir.flowershop.security.core.TokenProvider;
import by.bsuir.flowershop.security.core.filter.ExceptionHandlerFilter;
import by.bsuir.flowershop.security.core.filter.TokenAuthenticationFilter;
import by.bsuir.flowershop.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import by.bsuir.flowershop.security.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanProducer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public TokenAuthenticationFilter tokenAuthenticationFilter(TokenProvider tokenProvider,
                                                               CustomUserDetailsServiceImpl customUserDetailsServiceImpl) {
        return new TokenAuthenticationFilter(tokenProvider, customUserDetailsServiceImpl);
    }

    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

}
