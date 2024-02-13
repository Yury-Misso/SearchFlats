package by.itacademy.report.core.validator.uuidValidatorArr.uuidValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDArrConstraintValidator implements ConstraintValidator<ValidUUIDArr, String[]> {
    @Override
    public void initialize(ValidUUIDArr constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String[] uuidValue, ConstraintValidatorContext context) {
        if (uuidValue == null || uuidValue.length == 0) {
            return false;
        }
        try {
            for (String val : uuidValue) {
                UUID.fromString(val);
            }
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Невалидный UUID[]", e);
        }
    }
}
