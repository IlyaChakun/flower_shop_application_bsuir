package by.bsuir.flowershop.dto.validation.annotation;

import by.bsuir.flowershop.dto.validation.validator.PositiveLongValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = PositiveLongValidator.class)
@Target({METHOD, FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveLong {
    String message() default "The value must be positive and numeric!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}