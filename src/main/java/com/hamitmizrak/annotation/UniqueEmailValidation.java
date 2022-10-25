package com.hamitmizrak.annotation;

import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//lombok
@RequiredArgsConstructor
public class UniqueEmailValidation implements ConstraintValidator<UserRegisterUniqueEmail,String> {

    //repository
   private final IRegisterRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        RegisterEntity registerEntity=repository.findByEmail(email);
        if(registerEntity!=null)
            return false;
        return true;
    }
}
