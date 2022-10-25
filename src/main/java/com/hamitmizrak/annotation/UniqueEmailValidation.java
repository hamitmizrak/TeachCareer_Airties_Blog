package com.hamitmizrak.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidation implements ConstraintValidator<UserRegisterUniqueEmail,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
