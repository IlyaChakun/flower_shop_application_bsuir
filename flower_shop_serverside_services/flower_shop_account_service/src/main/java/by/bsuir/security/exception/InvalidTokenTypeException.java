package by.bsuir.security.exception;

import by.bsuir.payload.AbstractException;
import org.springframework.http.HttpStatus;

public class InvalidTokenTypeException extends AbstractException {

    public InvalidTokenTypeException(String errorDescription) {
        super(HttpStatus.UNAUTHORIZED.value(), "invalid_token_type", errorDescription);
    }

}
