package com.wktechnology.bloodbank.domain.dto;

import lombok.Builder;

import java.util.List;

public record DonatesPerBloodReceiverDTO(String bloodType, List<BloodTypeAmountDTO> donates) {

    @Builder(toBuilder = true)
    public DonatesPerBloodReceiverDTO{}

    public Long getTotal(){
        return donates.stream().map(BloodTypeAmountDTO::amount).reduce(0L, Long::sum);
    }

}
