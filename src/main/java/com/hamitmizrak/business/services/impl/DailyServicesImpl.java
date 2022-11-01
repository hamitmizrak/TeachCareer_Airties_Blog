package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.DailyDto;
import com.hamitmizrak.business.services.IDailyServices;
import com.hamitmizrak.data.entity.DailyEntity;
import com.hamitmizrak.data.repository.IDailyRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
public class DailyServicesImpl implements IDailyServices {

    //injection
    private final IDailyRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // Model Mapper
    @Override
    public DailyDto entityToDto(DailyEntity registerEntity) {
        return modelMapperBean.modelMapperMethod().map(registerEntity, DailyDto.class);
    }

    @Override
    public DailyEntity dtoToEntity(DailyDto registerDto) {
        return modelMapperBean.modelMapperMethod().map(registerDto, DailyEntity.class);
    }

    //CREATE
    @Override
    public DailyDto createDaily(DailyDto registerDto) {
        registerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(registerDto.getPassword()));
        DailyEntity registerEntity = dtoToEntity(registerDto);
        repository.save(registerEntity);
        return registerDto;
    }

    //LIST
    @Override
    public List<DailyDto> listDaily() {
        List<DailyEntity> registerEntityList = repository.findAll();
        List<DailyDto> dtoList = new ArrayList<>();
        for (DailyEntity temp : registerEntityList) {
            DailyDto entityToDto = entityToDto(temp);
            dtoList.add(entityToDto);
        }
        return dtoList;
    }

    //FIND
    @Override
    public DailyDto findDaily(Long id) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        DailyDto entityToDto = entityToDto(registerEntity);
        return entityToDto;
    }

    //DELETE
    @Override
    public Map<String, Boolean> deleteDaily(Long id) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        repository.delete(registerEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    //UPDATE
    @Override
    public DailyDto updateDaily(Long id, DailyDto registerDto) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        if (registerEntity != null) {
            registerEntity.setDailyHeader(registerEntity.getDailyHeader());
            registerEntity.setDailyContent(registerEntity.getDailyContent());
            registerEntity.setEmail(registerEntity.getEmail());
            registerEntity.setPassword(registerEntity.getPassword());
            repository.save(registerEntity);
        }
        return registerDto;
    }
}
