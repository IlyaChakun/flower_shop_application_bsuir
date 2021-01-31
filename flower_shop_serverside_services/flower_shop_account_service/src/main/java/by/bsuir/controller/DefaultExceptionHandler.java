package by.bsuir.controller;


import by.bsuir.payload.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }


    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse httpServletResponse,
                                            AccessDeniedException ex) throws IOException {
        /*
         * Handles AccessDeniedException exceptions. Status code 403.
         */
        ex.getStackTrace();
        logger.error(ex.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permissions!");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex) {
        /*
         * Handles AccessDeniedException exceptions. Status code 403.
         */
        ex.getStackTrace();
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.FORBIDDEN.value(),
                        "forbidden",
                        "Email or password is not valid!"),
                HttpStatus.FORBIDDEN);
    }


}
