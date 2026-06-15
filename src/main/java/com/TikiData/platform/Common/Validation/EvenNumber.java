package com.TikiData.platform.Common.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EvenNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EvenNumber {
    String message() default "El número debe ser par";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
