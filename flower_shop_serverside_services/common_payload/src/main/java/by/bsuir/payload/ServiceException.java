package by.bsuir.payload;


public class ServiceException extends AbstractException {

    public ServiceException(Integer code, String error, String errorDescription) {
        super(code, error, errorDescription);
    }
}
