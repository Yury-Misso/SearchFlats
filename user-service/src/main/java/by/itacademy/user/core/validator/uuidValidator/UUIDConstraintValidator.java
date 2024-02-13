package by.itacademy.user.core.validator.uuidValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.UUID;

public class UUIDConstraintValidator implements ConstraintValidator<ValidUUID, String> {
    @Override
    public void initialize(ValidUUID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uuidValue, ConstraintValidatorContext context) {
        if (uuidValue == null || uuidValue.trim().isEmpty()) {
            return false;
        }
        try {
            UUID.fromString(uuidValue);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Невалидный UUID", e);
        }
    }
}
