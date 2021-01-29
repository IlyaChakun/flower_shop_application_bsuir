package by.bsuir.flowershop.security.exception;

import by.bsuir.flowershop.payload.AbstractException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends AbstractException {

    public DuplicateEmailException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "duplicate_email", errorDescription);
    }
}
