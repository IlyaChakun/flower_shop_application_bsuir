package by.bsuir.security.exception;

import by.bsuir.payload.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class ConfirmationTokeBrokenLinkException extends AbstractException {

    public ConfirmationTokeBrokenLinkException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "confirm_acc_token_broken_url", errorDescription);
    }
}
