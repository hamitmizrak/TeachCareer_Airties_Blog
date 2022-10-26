package com.hamitmizrak.business.iservices;

import com.hamitmizrak.business.dto.RegisterDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IRegisterServices {
    public String createSpeedData(Model model);
    public String validationGetRegister(Model model);
    public String validationPostRegister(RegisterDto registerDto, BindingResult bindingResult, Model model);
    public String registerList(Model model);
    public String registerFindById(Long id, Model model);
    public String registerDeleteById( Long id, Model model);
    public String updateGetRegister(Long id, Model model);
    public String updatePostRegister(Long id,RegisterDto registerDto,BindingResult bindingResult, Model model);
}
