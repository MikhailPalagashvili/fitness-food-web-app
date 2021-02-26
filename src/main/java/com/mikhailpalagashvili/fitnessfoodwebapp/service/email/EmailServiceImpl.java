package com.mikhailpalagashvili.fitnessfoodwebapp.service.email;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.model.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(final RegisterForm registerForm) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("fitnessfoodweabapp@gmail.com");
        simpleMailMessage.setTo(registerForm.getUserLoginInfo().getEmail());
        simpleMailMessage.setSubject("Fitness Food Generator Account Verification");
        simpleMailMessage.setText("Welcome " + registerForm.getUserName() + ", " +
                "\nWelcome to Fitness Food Generator. Please verify your account by clicking the link below:\n");
        javaMailSender.send(simpleMailMessage);
    }
}
