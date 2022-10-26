package com.hamitmizrak.business.iservices.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.RegisterDto;
import com.hamitmizrak.business.iservices.IRegisterServices;
import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//lombok
@RequiredArgsConstructor
@Log4j2

//asil isin yapan katman
@Service
@Transactional
public class RegisterServicesImpl implements IRegisterServices {

    //Inject
    private final IRegisterRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;


    // SPEED DATA
    // http://localhost:8080/speedData
    @Override
    @GetMapping("/speedData")
    public String createSpeedData(Model model) {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            UUID uuid = UUID.randomUUID();
            RegisterEntity registerEntity = RegisterEntity.builder()
                    .name("adı " + i)
                    .surname("soyadı " + i)
                    .password("root").email(uuid.toString().concat("@gmail.com")).build();
            repository.save(registerEntity);
            counter++;
        }
        model.addAttribute("key_dataset", counter + " tane Register Entity oluşturuldu");
        return "register_list";
    }

    // CREATE 2497-2588
    // http://localhost:8080/register/create
    @Override
    @GetMapping("/register/create")
    public String validationGetRegister(Model model) {
        model.addAttribute("key_register", new RegisterDto());
        return "register_create";
    }

    //CREATE
    // http://localhost:8080/register/create
    @Override
    @PostMapping("/register/create")
    public String validationPostRegister(@Valid @ModelAttribute("key_register") RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "register_create";
        }
        //eğer valiadtion bir hata yoksa
        model.addAttribute("register_success", "Üye Kaydı Başarılı " + registerDto);
        log.info("Başarılı " + registerDto);

        //Database
        //masking password
        registerDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(registerDto.getPassword()));

        //model mapper
        RegisterEntity registerEntity = modelMapperBean.modelMapperMethod().map(registerDto, RegisterEntity.class);
        //model mapper yerine biz yazarsak
        //RegisterEntity registerEntity=new RegisterEntity();
        //registerEntity.setId(registerDto.getId());
        //registerEntity.setName(registerDto.getName());
        //registerEntity.setSurname(registerDto.getSurname());
        //registerEntity.setEmail(registerDto.getEmail());
        //registerEntity.setPassword(registerDto.getPassword());
        try {
            if (registerEntity != null) {
                repository.save(registerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //File
        return "success";
    }


    // LIST
    // http://localhost:8080/register/list
    @GetMapping("/register/list")
    @Override
    public String registerList(Model model) {
        List<RegisterEntity> list = repository.findAll();
        model.addAttribute("register_list", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        return "register_list";
    }

    // FIND
    // http://localhost:8080/register/find
    // http://localhost:8080/register/find/1
    @GetMapping( "/register/find/{id}")
    @Override
    public String registerFindById(@PathVariable(name = "id") Long id, Model model) {
        //1.YOL
        /*Optional<RegisterEntity> findData = repository.findById(id);
        if (findData.isPresent()) {
            return "Data: " + findData.get();
        } else {
            return id + " numaralı Data: Bulunamadı  ";
        }*/

        //2.YOL
        RegisterEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        model.addAttribute("register_find", registerEntity);
        return "register_detail_page";
    }

    // DELETE
    // http://localhost:8080/register/delete
    // http://localhost:8080/register/delete/1
    @GetMapping({"/register/delete", "/register/delete/{id}"})
    @Override
    public String registerDeleteById(@PathVariable(name = "id", required = false) Long id, Model model) {
        RegisterEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (registerEntity != null) {
            repository.deleteById(id);
            model.addAttribute("key_delete", registerEntity + " silindi");
        } else
            model.addAttribute("key_delete", id + " numaralı veri yoktur");
        return "redirect:/register/list";
    }

    //UPDATE
    // http://localhost:8080/update/register
    @GetMapping("/register/update/{id}")
    @Override
    public String updateGetRegister(@PathVariable(name = "id") Long id, Model model) {
        RegisterEntity registerEntityFind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " nolu kayıt yoktur"));
        if (registerEntityFind != null) {
            model.addAttribute("key_update", registerEntityFind);
        } else
            model.addAttribute("key_update", id + " numaralı veri yoktur");
        return "register_update";
    }

    //UPDATE
    // http://localhost:8080/update/register
    @PostMapping("/register/update/{id}")
    @Override
    public String updatePostRegister(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("key_update") RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "register_update";
        }
        RegisterEntity registerEntity = modelMapperBean.modelMapperMethod().map(registerDto, RegisterEntity.class);
        try {
            if (registerEntity != null) {
                repository.save(registerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/register/list";
    }

}
