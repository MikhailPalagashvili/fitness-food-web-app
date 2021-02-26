package com.mikhailpalagashvili.fitnessfoodwebapp.controller;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.UserInfoService;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.email.EmailServiceImpl;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.GroupOrder;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.EmailValidator;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.UserNameValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/register.html")
@SessionAttributes("registerForm")
public class RegisterController {
    private final UserInfoService userInfoService;

    private final EmailServiceImpl emailService;

    @Autowired
    public RegisterController(final UserInfoService userInfoService, final EmailServiceImpl emailService) {
        this.userInfoService = userInfoService;
        this.emailService = emailService;
    }

    @GetMapping
    public String getRegister(final RegisterForm registerForm, final Model model) {
        model.addAttribute(registerForm);
        return "register";
    }

    @GetMapping
    public String getRegister(final RegisterForm registerForm) {
        return "register";
    }

    @PostMapping
    public String postRegister(@ModelAttribute @Validated(GroupOrder.class) final RegisterForm registerForm,
                               final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return getRegister(registerForm);
        }
        emailService.sendEmail(registerForm);
//        redirectAttributes.addFlashAttribute("registerForm", registerForm);
        final UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setEmail(registerForm.getUserLoginInfo().getEmail());
        userLoginInfo.setPassword(registerForm.getUserLoginInfo().getPassword());
        final boolean isInserted = userInfoService.insert(userLoginInfo);
        if (isInserted) System.out.println("inserted");
        else System.out.println("not inserted");
        System.out.println(registerForm);
        return "redirect:/calculate-calories.html";
    }

    @InitBinder
    public void initBinder(final WebDataBinder webDataBinder) {
        System.out.println("inside the init binder method");
        final UserNameValidator userNameValidator = new UserNameValidator();
        webDataBinder.addValidators(userNameValidator);
        final EmailValidator emailValidator = new EmailValidator();
        webDataBinder.addValidators(emailValidator);
    }
}
