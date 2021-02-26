package com.mikhailpalagashvili.fitnessfoodwebapp.domain.model;


import com.mikhailpalagashvili.fitnessfoodwebapp.validation.ValidGroup1;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.ValidGroup2;
import com.mikhailpalagashvili.fitnessfoodwebapp.validation.ValidGroup3;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginInfo {
    @Email(groups = ValidGroup1.class)
    private String email;
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 8, max = 100, groups = ValidGroup2.class)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", groups = ValidGroup3.class)
    private String password;
}
