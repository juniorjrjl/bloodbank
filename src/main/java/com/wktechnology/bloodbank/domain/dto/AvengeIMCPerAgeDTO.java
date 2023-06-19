package com.wktechnology.bloodbank.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

public record AvengeIMCPerAgeDTO(BigDecimal avengeIMC, String ageRange) {

    @Builder(toBuilder = true)
    public AvengeIMCPerAgeDTO {}

}
