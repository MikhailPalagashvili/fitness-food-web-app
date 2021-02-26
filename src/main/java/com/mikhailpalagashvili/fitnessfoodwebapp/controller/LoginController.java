package com.mikhailpalagashvili.fitnessfoodwebapp.controller;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.jdbc.JDBCUserInfoRepository;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.UserInfoService;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.GroupOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping({"/", "/login.html"})
public class LoginController {
    private final UserInfoService userInfoService;

    @Autowired
    public LoginController(final UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping
    public String getLogin(@ModelAttribute final UserLoginInfo userLoginInfo) {
        return "login";
    }

    @PostMapping
    public String postLogin(@ModelAttribute @Validated(GroupOrder.class) final UserLoginInfo userLoginInfo, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return getLogin(userLoginInfo);
        }
        userInfoService.insert(userLoginInfo);
        return new HomeController(new UserInfoService(new JDBCUserInfoRepository(new JdbcTemplate()))).getHome(userLoginInfo, bindingResult);
    }
}
