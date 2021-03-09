package com.mikhailpalagashvili.fitnessfoodwebapp.controller;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.UserLoginService;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.email.EmailServiceImpl;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.GroupOrder;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.EmailValidator;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.UserNameValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping({"/register", "/register.html"})
@SessionAttributes("registerForm")
public class RegisterController {
    private final UserLoginService userLoginService;
    private final EmailServiceImpl emailService;

    @Autowired
    public RegisterController(final UserLoginService userLoginService, final EmailServiceImpl emailService) {
        this.userLoginService = userLoginService;
        this.emailService = emailService;
    }

    @ModelAttribute("registerForm")
    public RegisterForm registerForm() {
        return new RegisterForm();
    }

    @GetMapping
    public String getRegister() {
        return "register";
    }

    @PostMapping
    public String postRegister(@ModelAttribute @Validated(GroupOrder.class) final RegisterForm registerForm,
                               final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "/register";
        }
        emailService.sendEmail(registerForm);
        final UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setEmail(registerForm.getUserLoginInfo().getEmail());
        userLoginInfo.setPassword(registerForm.getUserLoginInfo().getPassword());
        final boolean isTaken = userLoginService.selectOne(userLoginInfo.getEmail()) != null;
        if (isTaken) {

            System.out.println("taken");
            return "/register";
        }
        System.out.println("not taken");
        userLoginService.insert(userLoginInfo);
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
