package by.bsuir.security.exception;

import by.bsuir.payload.AbstractException;
import org.springframework.http.HttpStatus;

public class AccessTokenException extends AbstractException {

    public AccessTokenException(String errorDescription) {
        super(HttpStatus.UNAUTHORIZED.value(), "invalid_token", errorDescription);
    }

}
