package com.wktechnology.bloodbank.domain.dto;

import lombok.Builder;

public record BloodTypeAvengeAgeDTO(String bloodType, Long avenge) {

    @Builder(toBuilder = true)
    public BloodTypeAvengeAgeDTO{}

}
