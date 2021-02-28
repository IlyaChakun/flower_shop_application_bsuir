package by.bsuir.service.exception;

import by.bsuir.payload.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends AbstractException {

    public DuplicateEmailException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "duplicate_email", errorDescription);
    }
}
