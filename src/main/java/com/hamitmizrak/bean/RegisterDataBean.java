package com.hamitmizrak.bean;

import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.iservices.IRegisterServices;
import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Configuration
public class RegisterDataBean {
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;
    @Bean
    CommandLineRunner speedRegister(IRegisterRepository repository){
        return (args)->{
            for (int i = 1; i <=5 ; i++) {
                UUID uuid=UUID.randomUUID();
                RegisterDto registerDto=RegisterDto.builder()
                        .email(uuid.toString().concat("@hotmail.com"))
                        .password(passwordEncoderBean.passwordEncoderMethod().encode("root"+i))
                        .name("adı"+i)
                        .surname("soyadı"+i)
                        .createdDate(new Date(System.currentTimeMillis() ))
                        .build();
                RegisterEntity registerEntity=modelMapperBean.modelMapperMethod().map(registerDto,RegisterEntity.class);
                repository.save(registerEntity);
            }

        };
    }
}
