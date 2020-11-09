package by.bsuir.dto.validation.validator;



import by.bsuir.dto.validation.annotation.ValidPhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.bsuir.dto.validation.validator.CommonHolder.PHONE_PATTER;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {


    private static final Pattern PATTERN = Pattern.compile(PHONE_PATTER);

    @Override
    public boolean isValid(final String phoneNumber, final ConstraintValidatorContext context) {
        if (Objects.nonNull(phoneNumber)) {
            return validatePhone(phoneNumber);
        } else {
            return false;
        }
    }

    private boolean validatePhone(final String phoneNumber) {
        Matcher matcher = PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

}

