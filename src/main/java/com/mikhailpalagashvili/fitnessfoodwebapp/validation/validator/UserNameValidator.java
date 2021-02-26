package com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserNameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //check if field is null
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.empty", "User Name cannot be empty");
        //check if field has white spaces
        String userName = ((RegisterForm) target).getUserName();
        if (userName.contains(" "))
            errors.rejectValue("userName", "userName.invalid", "User Name cannot have any spaces");

    }
}
