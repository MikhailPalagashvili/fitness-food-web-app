package com.mikhailpalagashvili.fitnessfoodwebapp.controller;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.UserLoginService;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.GroupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/login.html"})
@SessionAttributes("userLoginInfo")
public class LoginController {
    private final UserLoginService userLoginService;

    @Autowired
    public LoginController(final UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @ModelAttribute("userLoginInfo")
    public UserLoginInfo userLoginInfo() {
        return new UserLoginInfo();
    }

    @GetMapping
    public String getLogin() {
        return "login";
    }

    @PostMapping
    public String postLogin(@ModelAttribute @Validated(GroupOrder.class) final UserLoginInfo userLoginInfo,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "login";
        }
        userLoginService.selectOne(userLoginInfo.getEmail());

        return "redirect:/home";
    }
}
