package com.hamitmizrak.ui.api.impl;

import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.services.IRegisterServices;
import com.hamitmizrak.ui.api.IRegisterApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@Log4j2
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/reg/v1")
@CrossOrigin
public class RegisterApiImpl implements IRegisterApi {

    //injection services
    private final IRegisterServices services;

    //http://localhost:8080/api/reg/v1/register/create
    //CREATE
    @Override
    @PostMapping("register/create")
    public ResponseEntity<?>  createRegister(@Valid @RequestBody RegisterDto registerDto) {
        services.createRegister(registerDto);
        return ResponseEntity.ok(registerDto);
    }

    //http://localhost:8080/api/reg/v1/register/list
    //LIST
    @Override
    @GetMapping("register/list")
    public ResponseEntity<List<RegisterDto>>  listRegister() {
        return ResponseEntity.ok(services.listRegister());
    }


    //http://localhost:8080/api/reg/v1/register/find/1
    //FIND
    @Override
    @GetMapping("register/find/{id}")
    public ResponseEntity<RegisterDto> findRegister(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(services.findRegister(id));
    }


    //http://localhost:8080/api/reg/v1/register/delete/1
    //DELETE
    @Override
    @DeleteMapping("register/delete/{id}")
    public ResponseEntity <Map<String, Boolean>> deleteRegister(@PathVariable(name = "id") Long id) {
        services.deleteRegister(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok( response);
    }


    //http://localhost:8080/api/reg/v1/register/update/1
    //UPDATE
    @Override
    @PutMapping("register/update/{id}")
    public ResponseEntity<RegisterDto>  updateRegister(@PathVariable(name = "id") Long id, @Valid @RequestBody RegisterDto registerDto) {
        services.updateRegister(id,registerDto);
        return ResponseEntity.ok(registerDto);
    }
}
