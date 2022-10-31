package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.data.entity.RegisterEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface IRegisterServices {

    RegisterDto entityToDto(RegisterEntity registerEntity);

    RegisterEntity dtoToEntity(RegisterDto registerDto);

    //CREATE
    RegisterDto createRegister(@RequestBody RegisterDto registerDto);

    //LIST
    List<RegisterDto> listRegister();

    //FIND
    RegisterDto findRegister(@PathVariable(name = "id") Long id);

    //DELETE
    Map<String,Boolean> deleteRegister(@PathVariable(name = "id") Long id);

    //UPDATE
    RegisterDto updateRegister(@PathVariable(name = "id") Long id, @RequestBody RegisterDto registerDto);
}
