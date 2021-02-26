package com.mikhailpalagashvili.fitnessfoodwebapp.domain.api;

import lombok.Data;

@Data
public class PhoneNumber {
    private String areaCode;
    private String prefix;
    private String lineNumber;

    @Override
    public String toString() {
        return areaCode + ("-") + prefix + ("-") + lineNumber;
    }
}
