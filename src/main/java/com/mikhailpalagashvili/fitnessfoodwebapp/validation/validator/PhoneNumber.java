package com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator;

import com.mikhailpalagashvili.fitnessfoodwebapp.validation.validator.constraint.PhoneNumberValidatorConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidatorConstraint.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String areaCode() default "xxx";

    String prefix() default "xxx";

    String lineNumber() default "xxxx";

    String message() default "typeMismatch.registerForm.phoneNumber";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
