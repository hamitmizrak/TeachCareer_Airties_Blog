package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.data.entity.RegisterEntity;

import java.util.List;
import java.util.Map;

public interface IRegisterServices {

    RegisterDto entityToDto(RegisterEntity registerEntity);

    RegisterEntity dtoToEntity(RegisterDto registerDto);

    //CREATE
    RegisterDto createRegister( RegisterDto registerDto);

    //LIST
    List<RegisterDto> listRegister();

    //FIND
    RegisterDto findRegister(Long id);

    //DELETE
    Map<String,Boolean> deleteRegister( Long id);

    //UPDATE
    RegisterDto updateRegister( Long id, RegisterDto registerDto);
}
