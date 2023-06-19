package com.wktechnology.bloodbank.domain.dto;

import com.wktechnology.bloodbank.domain.entity.SexEnum;
import lombok.Getter;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@Getter
public class IMCAndSexDTO {

    private final SexEnum sex;
    private final BigDecimal imc;

    public IMCAndSexDTO(final SexEnum sex, final BigDecimal weight, final BigDecimal height){
        this.sex = sex;
        var doubleHeight = height.multiply(height);
        imc = weight.divide(doubleHeight, 2, HALF_UP);
    }

}
