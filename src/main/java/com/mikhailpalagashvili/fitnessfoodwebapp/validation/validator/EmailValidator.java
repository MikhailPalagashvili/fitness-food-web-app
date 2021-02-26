package com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final boolean VALID = true;
    private static final boolean INVALID = false;

    public static boolean isValidEmailString(String emailStr) {
        Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
        Matcher matcher = pattern.matcher(emailStr);
        return matcher.matches() ? VALID : INVALID;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace
                (errors, "userLoginInfo.email", "email.empty");
        //check email format
        String email = ((RegisterForm) target).getUserLoginInfo().getEmail();
        if (!isValidEmailString(email))
            errors.rejectValue("userLoginInfo.email", "invalid.email");


    }
}
