package com.wktechnology.bloodbank.domain.mapper;

import com.wktechnology.bloodbank.api.controller.request.CandidateSaveBatchRequest;
import com.wktechnology.bloodbank.domain.dto.CandidateInfoBatchSaveDTO;
import com.wktechnology.bloodbank.domain.entity.AddressEntity;
import com.wktechnology.bloodbank.domain.entity.CandidateEntity;
import com.wktechnology.bloodbank.domain.entity.ContactEntity;
import com.wktechnology.bloodbank.domain.entity.SexEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

import static com.wktechnology.bloodbank.domain.entity.SexEnum.FEMALE;
import static com.wktechnology.bloodbank.domain.entity.SexEnum.MALE;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class CandidateMapper {

    @Mapping(target = "street", source = "address")
    @Mapping(target = "state", expression = "java(request.state().toUpperCase())")
    @Mapping(target = "sex", expression = "java(toSexEnum(request.sex()))")
    @Mapping(target = "cpf", expression = "java(request.cpf().replaceAll(\"\\\\D+\",\"\"))")
    @Mapping(target = "rg", expression = "java(request.rg().replaceAll(\"\\\\D+\",\"\"))")
    @Mapping(target = "cep", expression = "java(request.cep().replaceAll(\"\\\\D+\",\"\"))")
    @Mapping(target = "phone", expression = "java(request.phone().replaceAll(\"\\\\D+\",\"\"))")
    @Mapping(target = "cellphone", expression = "java(request.cellphone().replaceAll(\"\\\\D+\",\"\"))")
    public abstract CandidateInfoBatchSaveDTO toDTO(final CandidateSaveBatchRequest request);

    protected SexEnum toSexEnum(final String sex){
        return sex.toLowerCase().startsWith("f") ? FEMALE : MALE;
    }

    public CandidateEntity build(final CandidateInfoBatchSaveDTO candidate){
        var entity = toEntity(candidate);
        entity.getAddresses().forEach(a -> a.setCandidate(entity));
        entity.getContacts().forEach(a -> a.setCandidate(entity));
        return entity;
    }

    @Mapping(target = "contacts", expression = "java(toContactsEntity(candidate))")
    @Mapping(target = "addresses", expression = "java(java.util.Set.of(toAddressEntity(candidate)))")
    @Mapping(target = "bloodType", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    protected abstract CandidateEntity toEntity(final CandidateInfoBatchSaveDTO candidate);

    protected Set<ContactEntity> toContactsEntity(final CandidateInfoBatchSaveDTO candidate){
        var phone = toPhone(candidate.phone());
        var cellphone = toCellphone(candidate.cellphone());
        var email = toEmail(candidate.email());
        return Set.of(phone, cellphone, email);
    }

    @Mapping(target = "type", constant = "phone")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    protected abstract ContactEntity toPhone(final String value);

    @Mapping(target = "type", constant = "cellphone")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    protected abstract ContactEntity toCellphone(final String value);

    @Mapping(target = "type", constant = "email")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    protected abstract ContactEntity toEmail(final String value);


    @Mapping(target = "city", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    protected abstract AddressEntity toAddressEntity(final CandidateInfoBatchSaveDTO candidate);

}
