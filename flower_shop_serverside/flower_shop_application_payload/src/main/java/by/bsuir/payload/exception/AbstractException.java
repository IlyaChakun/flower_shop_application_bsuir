package by.bsuir.payload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AbstractException extends RuntimeException {

    private final Integer code;

    private final String error;

    private final String errorDescription;

}
