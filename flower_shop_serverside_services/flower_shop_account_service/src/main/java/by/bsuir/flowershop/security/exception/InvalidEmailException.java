package by.bsuir.flowershop.security.exception;

import by.bsuir.flowershop.payload.AbstractException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends AbstractException {

    public InvalidEmailException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "invalid_email", errorDescription);
    }

}
