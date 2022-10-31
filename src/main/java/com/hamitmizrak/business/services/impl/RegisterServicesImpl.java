package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.services.IRegisterServices;
import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public RegisterDto entityToDto(RegisterEntity registerEntity) {
        return modelMapperBean.modelMapperMethod().map(registerEntity, RegisterDto.class);
    }

    @Override
    public RegisterEntity dtoToEntity(RegisterDto registerDto) {
        return modelMapperBean.modelMapperMethod().map(registerDto, RegisterEntity.class);
    }

    //CREATE
    @Override
    @PostMapping("/services/register/create")
    public RegisterDto createRegister(@RequestBody RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(registerDto.getPassword()));
        RegisterEntity registerEntity = dtoToEntity(registerDto);
        repository.save(registerEntity);
        return registerDto;
    }

    //LIST
    @Override
    @GetMapping("/services/register/list")
    public List<RegisterDto> listRegister() {
        List<RegisterEntity> registerEntityList = repository.findAll();
        List<RegisterDto> dtoList = new ArrayList<>();
        for (RegisterEntity temp : registerEntityList) {
            RegisterDto entityToDto = entityToDto(temp);
            dtoList.add(entityToDto);
        }
        return dtoList;
    }

    //FIND
    @Override
    @GetMapping("/services/register/find/{id}")
    public RegisterDto findRegister(@PathVariable(name = "id") Long id) {
        RegisterEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        RegisterDto entityToDto = entityToDto(registerEntity);
        return entityToDto;
    }

    //DELETE
    @Override
    @DeleteMapping("/services/register/delete/{id}")
    public Map<String, Boolean> deleteRegister(@PathVariable(name = "id") Long id) {
        RegisterEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        repository.delete(registerEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    //UPDATE
    @Override
    @PutMapping("/services/register/update/{id}")
    public RegisterDto updateRegister(@PathVariable(name = "id") Long id, @RequestBody RegisterDto registerDto) {
        RegisterEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        if (registerEntity != null) {
            registerEntity.setName(registerEntity.getName());
            registerEntity.setSurname(registerEntity.getSurname());
            registerEntity.setEmail(registerEntity.getEmail());
            registerEntity.setPassword(registerEntity.getPassword());
            repository.save(registerEntity);
        }
        return registerDto;
    }
}
