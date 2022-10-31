package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.services.IRegisterServices;
import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
//asıl iş katmanı olan yer
public class RegisterServicesImpl implements IRegisterServices {

    //injection
    private final IRegisterRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // Model Mapper
    @Override
    public RegisterDto entityToDto(RegisterEntity registerEntity){
        return modelMapperBean.modelMapperMethod().map(registerEntity,RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto){
        return modelMapperBean.modelMapperMethod().map(registerDto,RegisterEntity.class);
    }

    //CREATE
    @Override
    @PostMapping("register/create")
    public RegisterDto createRegister( @RequestBody RegisterDto registerDto){
        registerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(registerDto.getPassword()));
        RegisterEntity registerEntity=dtoToEntity(registerDto);
        repository.save(registerEntity);
        return registerDto;
    }

    //DELETE

    //LIST

    //FIND


}
