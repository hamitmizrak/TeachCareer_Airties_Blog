package com.hamitmizrak.annotation;

import com.hamitmizrak.data.entity.DailyEntity;
import com.hamitmizrak.data.repository.IDailyRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//lombok
@RequiredArgsConstructor
public class UniqueEmailValidation implements ConstraintValidator<UserRegisterUniqueEmail,String> {

    //repository
   private final IDailyRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        DailyEntity registerEntity=repository.findByEmail(email);
        if(registerEntity!=null)
            return false;
        return true;
    }
}
