package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.data.entity.RegisterEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IRegisterServices {

    RegisterDto entityToDto(RegisterEntity registerEntity);

    RegisterEntity dtoToEntity(RegisterDto registerDto);

    //CREATE
    @PostMapping("register/create")
    RegisterDto createRegister(@RequestBody RegisterDto registerDto);
}
