package com.hamitmizrak.ui.api;
import com.hamitmizrak.business.dto.RegisterDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface IRegisterApi {
    //CREATE
    ResponseEntity<?> createRegister(RegisterDto registerDto);

    //LIST
    ResponseEntity<List<RegisterDto>>  listRegister();

    //FIND
    ResponseEntity<RegisterDto> findRegister(Long id);

    //DELETE
    ResponseEntity <Map<String, Boolean>> deleteRegister( Long id);

    //UPDATE
    ResponseEntity<RegisterDto>  updateRegister( Long id,  RegisterDto registerDto);
}
