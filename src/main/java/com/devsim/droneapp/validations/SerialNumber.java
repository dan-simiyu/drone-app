package com.devsim.droneapp.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SerialNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialNumber {
    String message() default "Serial number is invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String regex() default "[a-zA-Z]+";
}
