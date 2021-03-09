package com.mikhailpalagashvili.fitnessfoodwebapp.service;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import com.mikhailpalagashvili.fitnessfoodwebapp.repository.RegisterFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterFormService {
    private RegisterFormRepository registerFormRepository;

    @Autowired
    public RegisterFormService(RegisterFormRepository registerFormRepository) {
        this.registerFormRepository = registerFormRepository;
    }

    public boolean insert(RegisterForm registerForm) {
        int rowNumber = registerFormRepository.insert(registerForm);
        return rowNumber > 0;
    }
}
