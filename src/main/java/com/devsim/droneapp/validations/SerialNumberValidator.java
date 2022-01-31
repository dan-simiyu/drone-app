package com.devsim.droneapp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class SerialNumberValidator implements ConstraintValidator<SerialNumber, String> {
    private Pattern pattern;
    @Override
    public void initialize(SerialNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        pattern = Pattern.compile(constraintAnnotation.regex());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return pattern.asPredicate().test(value);
    }
}
