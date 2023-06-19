package com.wktechnology.bloodbank.domain.dto;

import com.wktechnology.bloodbank.domain.entity.SexEnum;
import lombok.Builder;

import java.math.BigDecimal;

public record PercentObesePerSexDTO(SexEnum sex, BigDecimal percent) {

    @Builder(toBuilder = true)
    public PercentObesePerSexDTO{}

}
