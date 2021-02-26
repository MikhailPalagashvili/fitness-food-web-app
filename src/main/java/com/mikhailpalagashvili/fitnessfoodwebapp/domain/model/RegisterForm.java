package com.mikhailpalagashvili.fitnessfoodwebapp.domain.model;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.api.PhoneNumber;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.ValidGroup1;
import lombok.Data;

import javax.validation.Valid;

@Data
public class RegisterForm {
    private Long userId;
    private String userName;
    @Valid
    private UserLoginInfo userLoginInfo;
    @com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.PhoneNumber(groups = ValidGroup1.class)
    private PhoneNumber phoneNumber;
}
