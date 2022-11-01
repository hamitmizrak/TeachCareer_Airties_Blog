package com.hamitmizrak.ui.mvc;
import com.hamitmizrak.business.dto.DailyDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IDailyController {
    public String createSpeedData(Model model);
    public String validationGetDaily(Model model);
    public String validationPostDaily(DailyDto dailyDto, BindingResult bindingResult, Model model);
    public String dailyList(Model model);
    public String dailyFindById(Long id, Model model);
    public String dailyDeleteById( Long id, Model model);
    public String updateGetDaily(Long id, Model model);
    public String updatePostDaily(Long id, DailyDto dailyDto, BindingResult bindingResult, Model model);
    }
