package by.bsuir.controller;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.exception.IllegalRequestException;
import by.bsuir.payload.exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.Objects;

final class ControllerHelper {

    private ControllerHelper() {
    }

    static void checkBindingResultAndThrowExceptionIfInvalid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalRequestException(result.getFieldErrors());
        }
    }

    static void isIdInsideDtoOrThrowException(AbstractDTO abstractDTO){
        if (Objects.isNull(abstractDTO.getId())){
            throw new ControllerException(HttpStatus.CONFLICT.value(),
                    "entity_came_without_id",
                    "The entity from UI came without Id!");
        }
    }
}
