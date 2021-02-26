package com.mikhailpalagashvili.fitnessfoodwebapp.formatter;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.api.PhoneNumber;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PhoneNumberFormatter implements Formatter<PhoneNumber> {
    @Override
    public PhoneNumber parse(String completePhoneNumber, Locale locale) throws ParseException {
        System.out.println("inside the parse method in formatter");
        String[] phoneParts = completePhoneNumber.split("-");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setAreaCode(phoneParts[0]);
        phoneNumber.setPrefix(phoneParts[1]);
        phoneNumber.setLineNumber(phoneParts[2]);
        return phoneNumber;
    }

    @Override
    public String print(PhoneNumber phoneNumber, Locale locale) {
        return phoneNumber.toString();
    }
}
