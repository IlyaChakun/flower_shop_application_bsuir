package by.bsuir.payload.exception;

public class ControllerException extends AbstractException {

    public ControllerException(Integer code, String error, String errorDescription) {
        super(code, error, errorDescription);
    }
}
