package by.bsuir.payload;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends AbstractException {

    public ResourceNotFoundException(String errorDescription) {
        super(HttpStatus.NOT_FOUND.value(), "object_not_found", errorDescription);
    }

}
