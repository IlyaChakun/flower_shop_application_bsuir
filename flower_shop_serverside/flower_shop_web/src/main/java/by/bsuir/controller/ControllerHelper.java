package by.bsuir.controller;

import by.bsuir.exception.IllegalRequestException;
import org.springframework.validation.BindingResult;

final class ControllerHelper {

    private ControllerHelper() {
    }

    static void checkBindingResultAndThrowExceptionIfInvalid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }
    }
}
