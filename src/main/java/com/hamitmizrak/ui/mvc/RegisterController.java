package com.hamitmizrak.ui.mvc;

import com.hamitmizrak.business.dto.RegisterDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Log4j2
public class RegisterController {

    // http://localhost:8080/validation/register
    @GetMapping("/validation/register")
    public String validationGetRegister(Model model){
        model.addAttribute("key_register",new RegisterDto());
        return "register";
    }

    // http://localhost:8080/validation/register
    @PostMapping("/validation/register")
    public String validationPostRegister(@Valid @ModelAttribute("key_register") RegisterDto registerDto,
            BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            log.error("HATA: "+bindingResult);
            return "register";
        }
        //eğer valiadtion bir hata yoksa
        model.addAttribute("register_success","Üye Kaydı Başarılı "+registerDto);
        log.info("Başarılı "+registerDto);

        //Database
        //File
        return "success";
    }

}
