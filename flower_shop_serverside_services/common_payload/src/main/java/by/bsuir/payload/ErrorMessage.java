package by.bsuir.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public final class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer code;

    private final String error;

    private final String errorDescription;

}
