package com.TikiData.platform.Common.Validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenNumberValidator implements ConstraintValidator<EvenNumber, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        if (value == null){
            return true;
        }
        return value % 2 == 0;
    }
}
