package by.bsuir.flowershop.payload;

public class ControllerException extends AbstractException {

    public ControllerException(Integer code, String error, String errorDescription) {
        super(code, error, errorDescription);
    }
}
