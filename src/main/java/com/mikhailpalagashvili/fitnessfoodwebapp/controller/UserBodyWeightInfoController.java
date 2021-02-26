package com.mikhailpalagashvili.fitnessfoodwebapp.controller;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserBodyWeightInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/calculate-calories.html")
public class UserBodyWeightInfoController {
    @GetMapping
    public String getUserBodyWeightInfo(final HttpSession httpSession, final Model model) {
        RegisterForm registerForm = (RegisterForm) httpSession.getAttribute("registerForm");
        String userName = registerForm.getUserName();
        model.addAttribute("userName", userName);
        model.addAttribute("userBodyWeightInfo", new UserBodyWeightInfo());
        return "calculate-calories";
    }

    @PostMapping
    public String processUserBodyWeightInfo(@ModelAttribute final UserBodyWeightInfo userBodyWeightInfo, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrorList = bindingResult.getAllErrors();
            objectErrorList.forEach(System.out::println);
            return "calculate-calories";
        }
        return "process-calculate-calories";
    }
}
