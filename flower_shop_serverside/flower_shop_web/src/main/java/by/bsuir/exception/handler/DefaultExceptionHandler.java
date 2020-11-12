package by.bsuir.exception.handler;


import by.bsuir.exception.IllegalRequestException;
import by.bsuir.payload.exception.*;
import by.bsuir.security.exception.AccessTokenException;
import by.bsuir.security.exception.ConfirmationTokeBrokenLinkException;
import by.bsuir.security.exception.InvalidEmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
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
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "bad_request", "Request parameters are not valid!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException e) {
        /*
         * Exception occurs when passed id is null. Status 404.
         */
        logger.error(e.getMessage());
        String message = Objects.isNull(e.getMessage()) ? "" : e.getMessage();
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Resource not found!", message),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorMessage> handleServiceException(ServiceException e) {
        /* Handles service exception. */
        logger.error(e.getError());
        String message = Objects.isNull(e.getError()) ? "" : e.getError();
        return new ResponseEntity<>(
                new ErrorMessage(e.getCode(), message, e.getErrorDescription()), HttpStatus.valueOf(e.getCode()));
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<List<String>> handleValidation(IllegalRequestException e) {
        /*
         * Validation exceptions handling. Status code 400.
         */
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

    @ExceptionHandler({ConfirmationTokeBrokenLinkException.class, InvalidEmailException.class})
    public ResponseEntity<ErrorMessage> handleConfirmationTokeBrokenLinkException(AbstractException ex) {
        /*
         * Handles ConfirmationTokeBrokenLinkException exceptions. Status code 400.
         */
        System.out.println("ConfirmationTokeBrokenLinkException  InvalidEmailException handler");
        return
                new ResponseEntity<>(
                        new ErrorMessage(ex.getCode(), ex.getError(), ex.getErrorDescription()),
                        HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AccessTokenException.class)
    public ResponseEntity<ErrorMessage> handleExpiredAccessTokenException(AccessTokenException ex) {
        /*
         * Handles ExpiredAccessTokenException exceptions. Status code 401.
         */
        System.out.println("ExpiredAccessTokenException handler");
        return
                new ResponseEntity<>(
                        new ErrorMessage(401, "invalid_token", ex.getErrorDescription()),
                        HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse httpServletResponse,
                                            AccessDeniedException ex) throws IOException {
        /*
         * Handles AccessDeniedException exceptions. Status code 403.
         */
        logger.error(ex.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permissions!");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex) {
        /*
         * Handles AccessDeniedException exceptions. Status code 403.
         */
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.FORBIDDEN.value(),
                        "forbidden",
                        "Email or password is not valid!"),
                HttpStatus.FORBIDDEN);
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
