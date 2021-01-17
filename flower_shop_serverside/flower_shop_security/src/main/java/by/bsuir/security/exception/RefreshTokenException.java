package by.bsuir.security.exception;

import by.bsuir.payload.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class RefreshTokenException extends AbstractException {

    public RefreshTokenException(String errorDescription) {
        super(HttpStatus.UNAUTHORIZED.value(), "invalid_refresh_token", errorDescription);
    }

}
