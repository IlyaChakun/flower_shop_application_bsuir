package by.bsuir.flowershop.security.exception;


import by.bsuir.flowershop.payload.AbstractException;
import org.springframework.http.HttpStatus;

public class ConfirmationTokeBrokenLinkException extends AbstractException {

    public ConfirmationTokeBrokenLinkException(String errorDescription) {
        super(HttpStatus.BAD_REQUEST.value(), "confirm_acc_token_broken_url", errorDescription);
    }
}
