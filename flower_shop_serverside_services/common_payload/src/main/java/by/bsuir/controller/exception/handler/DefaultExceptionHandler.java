package by.bsuir.controller.exception.handler;


import by.bsuir.controller.exception.IllegalRequestException;
import by.bsuir.payload.AbstractException;
import by.bsuir.payload.ErrorMessage;
import by.bsuir.payload.ErrorMessageList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, BindException.class,
            UnsatisfiedServletRequestParameterException.class, IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> handleJsonMappingException(Exception e) {
        /*
         * Exception occurs when passed id is null. Status 400.
         */
        e.getStackTrace();
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "bad_request", "Request parameters are not valid!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<List<String>> handleValidation(IllegalRequestException e) {
        /*
         * Validation exceptions handling. Status code 400.
         */

        e.getStackTrace();

        List<String> errors = new ArrayList<>();
        e.getErrors().forEach(er -> errors.add(String.format("Incorrect value for field %s : '%s'. %s.", er.getField(),
                er.getRejectedValue(), er.getDefaultMessage())));
        if (Objects.nonNull(e.getMessage())) {
            errors.add(0, e.getMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessageList> handleConstraintViolationException(ConstraintViolationException e) {

        e.getStackTrace();

        logger.error(e.getMessage());
        List<ErrorMessage> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(er -> errors.add(
                new ErrorMessage(400, "constraint_error",
                        er.getMessageTemplate() +
                                " " + er.getPropertyPath() +
                                " " + er.getInvalidValue().toString())));

        return new ResponseEntity<>(
                new ErrorMessageList(HttpStatus.BAD_REQUEST.value(), "binding_result_errors",
                        "errors during binding", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AbstractException.class})
    public ResponseEntity<ErrorMessage> handleException(AbstractException ex) {
        /*

         */
        ex.printStackTrace();
        return
                new ResponseEntity<>(
                        new ErrorMessage(ex.getCode(), ex.getError(), ex.getErrorDescription()),
                        HttpStatus.valueOf(ex.getCode()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        /* Handles all other exceptions. Status code 500. */
        System.out.println("handleOthersException 500 works");
        e.printStackTrace();
        logger.error(e.getMessage());
        return
                new ResponseEntity<>(
                        new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "server_error",
                                "Internal server error."),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
