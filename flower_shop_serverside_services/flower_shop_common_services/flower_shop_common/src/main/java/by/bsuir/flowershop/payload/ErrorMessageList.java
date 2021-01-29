package by.bsuir.flowershop.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public final class ErrorMessageList implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer code;

    private final String error;

    private final String errorDescription;

    private final List<ErrorMessage> errors;

}
