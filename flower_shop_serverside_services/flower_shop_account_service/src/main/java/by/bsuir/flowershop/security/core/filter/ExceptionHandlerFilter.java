package by.bsuir.flowershop.security.core.filter;

import by.bsuir.flowershop.payload.AbstractException;
import by.bsuir.flowershop.payload.ErrorMessage;
import by.bsuir.flowershop.security.exception.AccessTokenException;
import by.bsuir.flowershop.security.exception.InvalidTokenTypeException;
import by.bsuir.flowershop.security.exception.RefreshTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor
/**
 * this filter must be first
 * if any exception will expire he will produce it and display in json format
 *
 * We can`t throw it to adviser because exception producing in Filter
 */
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {

        logger.info("ExceptionHandlerFilter works");

        try {
            filterChain.doFilter(request, response);
        } catch (RefreshTokenException | InvalidTokenTypeException | AccessTokenException ex) {

            logger.error("Could not set user authentication in security context", ex);

            // custom error response class used across my project
            ErrorMessage errorResponse = new ErrorMessage(ex.getCode(), ex.getError(), ex.getErrorDescription());

            response.setStatus(ex.getCode());
            response.setContentType("application/json");
            response.getWriter().write(convertObjectToJson(errorResponse));

        } catch (AbstractException ex) {
            ex.printStackTrace();
            logger.error("Could not set user authentication in security context", ex);
        }
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (Objects.isNull(object)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
