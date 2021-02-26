package com.mikhailpalagashvili.fitnessfoodwebapp.controller;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.UserLoginInfo;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/home.html")
public class HomeController {
    private final UserInfoService userInfoService;

    @Autowired
    public HomeController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping
    public String getHome(@Valid @ModelAttribute final UserLoginInfo userLoginInfo, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                System.out.println(objectError);
            }
            return "login";
        }


        return "home";
    }

    @GetMapping("/userList")
    public String getUserList(final Model model) {

        // Register a character string to display the user list in the content part
        model.addAttribute("contents", "login/userList :: userList_contents");

        // Search user list
        List<UserLoginInfo> userList = userInfoService.selectAll();

        // Register user list in Model
        model.addAttribute("userList", userList);

        // Get number of data
        int count = userInfoService.count();
        model.addAttribute("userListCount", count);

        return "homeLayout";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "redirect:/";
    }

    @GetMapping("/userList/csv")
    public String getUserListCsv(final Model model) {
        return getUserList(model);
    }
}
