package com.mikhailpalagashvili.fitnessfoodwebapp.domain.model;

import com.mikhailpalagashvili.fitnessfoodwebapp.domain.api.*;
import lombok.Data;

@Data
public class UserBodyWeightInfo {
    private Long userId;
    private Weight weight;
    private Height height;
    private BodyFatPercentage bodyFatPercentage;
    private DietPlan dietPlan;
    private NumberOfExercisingDays numberOfExercisingDays;
}
