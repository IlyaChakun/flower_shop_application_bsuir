package by.bsuir.flowershop.controller;

import by.bsuir.flowershop.controller.exception.IllegalRequestException;
import by.bsuir.flowershop.dto.model.AbstractDTO;
import by.bsuir.flowershop.payload.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.Objects;

final class ControllerHelper {

    private ControllerHelper() {
    }

    public static void checkBindingResultAndThrowExceptionIfInvalid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }
    }

    public static void isIdInsideDtoOrThrowException(AbstractDTO abstractDTO) {
        if (Objects.isNull(abstractDTO.getId())) {
            throw new ControllerException(HttpStatus.CONFLICT.value(),
                    "entity_came_without_id",
                    "The entity from UI came without Id!");
        }
    }
}
