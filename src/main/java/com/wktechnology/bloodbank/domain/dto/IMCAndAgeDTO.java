package com.wktechnology.bloodbank.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static java.math.RoundingMode.HALF_UP;

@Getter
public class IMCAndAgeDTO {

    private final BigDecimal imc;
    private final Integer age;

    public IMCAndAgeDTO(final LocalDate birthdate, final BigDecimal weight, final BigDecimal height){
        var doubleHeight = height.multiply(height);
        imc = weight.divide(doubleHeight, 2, HALF_UP);
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
    }

}
