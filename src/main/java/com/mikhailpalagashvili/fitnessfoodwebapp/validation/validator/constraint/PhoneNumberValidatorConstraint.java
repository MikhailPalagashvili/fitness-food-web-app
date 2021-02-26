package com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.constraint;

import com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidatorConstraint implements ConstraintValidator<PhoneNumber, com.mikhailpalagashvili.fitnessfoodwebapp.domain.api.PhoneNumber> {

    private String areaCode;
    private String prefix;
    private String lineNumber;

    @Override
    public void initialize(PhoneNumber phoneNumber) {
        System.out.println("inside the phone number validator constraint initialize method");
        this.areaCode = phoneNumber.areaCode();
        this.prefix = phoneNumber.prefix();
        this.lineNumber = phoneNumber.lineNumber();
    }

    @Override
    public boolean isValid(com.mikhailpalagashvili.fitnessfoodwebapp.domain.api.PhoneNumber phoneNumber, ConstraintValidatorContext context) {
        System.out.println("inside the phone number is valid method");
        System.out.println(phoneNumber);
        if (phoneNumber == null) return false;
        Pattern pattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber.getAreaCode() + phoneNumber.getPrefix() + phoneNumber.getLineNumber());
        return matcher.matches();
    }
}
