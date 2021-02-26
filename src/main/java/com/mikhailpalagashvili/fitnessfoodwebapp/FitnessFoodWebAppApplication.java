package com.mikhailpalagashvili.fitnessfoodwebapp;

import com.mikhailpalagashvili.fitnessfoodwebapp.formatter.PhoneNumberFormatter;
import com.mikhailpalagashvili.fitnessfoodwebapp.service.email.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
public class FitnessFoodWebAppApplication implements WebMvcConfigurer {
    private EmailServiceImpl emailService;

    public static void main(String[] args) {
        SpringApplication.run(FitnessFoodWebAppApplication.class, args);
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("inside add formatters method");
        registry.addFormatter(new PhoneNumberFormatter());
    }

    @Override
    public Validator getValidator() {
        System.out.println("inside get validator method");
        return validator();
    }

    @Bean
    public MessageSource messageSource() {
        System.out.println("inside message source method");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }


    @Bean
    public LocalValidatorFactoryBean validator() {
        System.out.println("inside local validator factory bean");
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}