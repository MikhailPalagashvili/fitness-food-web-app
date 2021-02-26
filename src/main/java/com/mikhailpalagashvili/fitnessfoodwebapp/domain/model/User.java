package com.mikhailpalagashvili.fitnessfoodwebapp.domain.model;

import lombok.Data;

import javax.validation.Valid;
import java.util.Date;

@Data
public class User {
    @Valid
    private Long userId;
    @Valid
    private Date createdAt;
    @Valid
    private RegisterForm registerForm;
    @Valid
    private UserBodyWeightInfo userBodyWeightInfo;
}
