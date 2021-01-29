package by.bsuir.flowershop.dto.validation.annotation;



import by.bsuir.flowershop.dto.validation.validator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPhone {

    String message() default "Некорректный номер телефона";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
