package com.wktechnology.bloodbank.domain.service.query.impl;

import com.wktechnology.bloodbank.domain.entity.CityEntity;
import com.wktechnology.bloodbank.domain.repository.CityRepository;
import com.wktechnology.bloodbank.domain.service.query.CityQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CityQueryServiceImpl implements CityQueryService {

    private final CityRepository repository;

    @Override
    public CityEntity findByNameAndStateAbbreviation(final String name, final String abbreviation) {
        return repository.findByNameAndState_Abbreviation(name, abbreviation)
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));
    }
}
