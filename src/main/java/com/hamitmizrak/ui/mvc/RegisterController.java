package com.hamitmizrak.ui.mvc;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.iservices.IRegisterServices;
import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

//lombok
@RequiredArgsConstructor
@Log4j2

//Controller
@Controller
//@RequestMapping("/controller")
public class RegisterController implements IRegisterController {

    //Inject
    private final IRegisterServices services;

    // SPEED DATA
    // http://localhost:8080/speedData
    @Override
    @GetMapping("/speedData")
    public String createSpeedData(Model model) {
        services.createSpeedData(model);
        return "register_list";
    }

    // CREATE 2497-2588
    // http://localhost:8080/register/create
    @Override
    @GetMapping("/register/create")
    public String validationGetRegister(Model model) {
        services.validationGetRegister(model);
        return "register_create";
    }

    //CREATE
    // http://localhost:8080/register/create
    @Override
    @PostMapping("/register/create")
    public String validationPostRegister(RegisterDto registerDto,BindingResult bindingResult, Model model) {
        services.validationPostRegister( registerDto, bindingResult, model);
        model.addAttribute("register_success", "Üye Kaydı Başarılı " + registerDto.getId());
        return "redirect:/register/list";
    }

    // LIST
    // http://localhost:8080/register/list
    @Override
    @GetMapping("/register/list")
    public String registerList(Model model) {
       services.registerList(model);
        return "register_list";
    }

    // FIND
    // http://localhost:8080/register/find/1
    @GetMapping( "/register/find/{id}")
    @Override
    public String registerFindById(@PathVariable(name = "id") Long id, Model model) {
        services.registerFindById(id,model);
        return "register_detail_page";
    }

    // DELETE
    // http://localhost:8080/register/delete/1
    @GetMapping({"/register/delete", "/register/delete/{id}"})
    @Override
    public String registerDeleteById(@PathVariable(name = "id", required = false) Long id, Model model) {
       services.registerDeleteById(id,model);
        return "redirect:/register/list";
    }

    // UPDATE
    // http://localhost:8080/update/register
    @GetMapping("/register/update/{id}")
    @Override
    public String updateGetRegister(@PathVariable(name = "id") Long id, Model model) {
       services.updateGetRegister(id,model);
        return "register_update";
    }

    //UPDATE
    // http://localhost:8080/update/register
    @PostMapping("/register/update/{id}")
    @Override
    public String updatePostRegister(@PathVariable(name = "id") Long id,RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "register_update";
        }
        services.updatePostRegister(id, registerDto);
        model.addAttribute("rename","kalem");
        return "redirect:/register/list";
    }
}
