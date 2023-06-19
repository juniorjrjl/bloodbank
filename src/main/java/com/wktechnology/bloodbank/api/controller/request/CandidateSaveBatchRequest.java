package com.wktechnology.bloodbank.api.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wktechnology.bloodbank.domain.entity.SexEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record CandidateSaveBatchRequest(
        @JsonProperty("nome")
        String name,
        @JsonProperty("cpf")
        String cpf,
        @JsonProperty("rg")
        String rg,
        @JsonProperty("data_nasc")
        @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy")
        LocalDate birthdate,
        @JsonProperty("sexo")
        String sex,
        @JsonProperty("mae")
        String mother,
        @JsonProperty("pai")
        String father,
        @JsonProperty("email")
        String email,
        @JsonProperty("cep")
        String cep,
        @JsonProperty("endereco")
        String address,
        @JsonProperty("numero")
        String number,
        @JsonProperty("bairro")
        String district,
        @JsonProperty("cidade")
        String city,
        @JsonProperty("estado")
        String state,
        @JsonProperty("telefone_fixo")
        String phone,
        @JsonProperty("celular")
        String cellphone,
        @JsonProperty("altura")
        BigDecimal height,
        @JsonProperty("peso")
        BigDecimal weight,
        @JsonProperty("tipo_sanguineo")
        String bloodType
) {

    @Builder(toBuilder = true)
    public CandidateSaveBatchRequest{}

}
