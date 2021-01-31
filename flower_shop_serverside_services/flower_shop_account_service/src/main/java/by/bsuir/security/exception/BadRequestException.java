package by.bsuir.security.exception;

import by.bsuir.payload.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AbstractException {

    public BadRequestException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "bad_request", errorDescription);
    }
}
