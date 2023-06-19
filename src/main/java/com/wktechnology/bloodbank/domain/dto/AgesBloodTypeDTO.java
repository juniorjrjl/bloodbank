package com.wktechnology.bloodbank.domain.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class AgesBloodTypeDTO {

    private final String bloodType;
    private final Integer age;

    public AgesBloodTypeDTO(final String bloodType, final LocalDate birthdate){
        this.bloodType = bloodType;
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
    }

}
