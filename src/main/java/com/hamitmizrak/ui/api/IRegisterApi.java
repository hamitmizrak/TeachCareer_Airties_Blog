package com.hamitmizrak.ui.api;

import com.hamitmizrak.business.dto.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface IRegisterApi {
    //CREATE
    @PostMapping("register/create")
    ResponseEntity<?> createRegister(@RequestBody RegisterDto registerDto);

    //LIST
    @GetMapping("register/list")
    ResponseEntity<List<RegisterDto>>  listRegister();

    //FIND
    @GetMapping("register/find/{id}")
    ResponseEntity<RegisterDto> findRegister(@PathVariable(name = "id") Long id);

    //DELETE
    @GetMapping("register/delete/{id}")
    ResponseEntity <Map<String, Boolean>> deleteRegister(@PathVariable(name = "id") Long id);

    //UPDATE
    @GetMapping("register/update/{id}")
    ResponseEntity<RegisterDto>  updateRegister(@PathVariable(name = "id") Long id, @RequestBody RegisterDto registerDto);
}
