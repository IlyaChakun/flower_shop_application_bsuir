package by.bsuir.flowershop.security.exception;

import by.bsuir.flowershop.payload.AbstractException;
import org.springframework.http.HttpStatus;

public class RefreshTokenException extends AbstractException {

    public RefreshTokenException(String errorDescription) {
        super(HttpStatus.UNAUTHORIZED.value(), "invalid_refresh_token", errorDescription);
    }

}
