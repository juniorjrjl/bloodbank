package com.wktechnology.bloodbank.domain.dto;

import com.wktechnology.bloodbank.domain.entity.SexEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CandidateInfoBatchSaveDTO(
    String name,
    String cpf,
    String rg,
    LocalDate birthdate,
    SexEnum sex,
    String mother,
    String father,
    String email,
    String cep,
    String street,
    String number,
    String district,
    String city,
    String state,
    String cellphone,
    String phone,
    BigDecimal height,
    BigDecimal weight,
    String bloodType
) {

    @Builder(toBuilder = true)
    public CandidateInfoBatchSaveDTO {}

}
