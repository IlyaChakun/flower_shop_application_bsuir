package by.bsuir.controller;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.exception.ControllerException;
import by.bsuir.exception.IllegalRequestException;
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


    static void checkIdInsideDto(AbstractDTO abstractDTO){
        if (Objects.isNull(abstractDTO.getId())){
            throw new ControllerException("Сущность с фронта пришла без id");
        }
    }
}
