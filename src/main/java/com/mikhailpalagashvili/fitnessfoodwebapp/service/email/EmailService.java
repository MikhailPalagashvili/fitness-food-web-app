package com.mikhailpalagashvili.fitnessfoodwebapp.service.email;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;

public interface EmailService {
    void sendEmail(RegisterForm registerForm);
}
