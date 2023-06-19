package com.wktechnology.bloodbank.domain.service.query.impl;

import com.wktechnology.bloodbank.domain.entity.CityEntity;
import com.wktechnology.bloodbank.domain.entity.StateEntity;
import com.wktechnology.bloodbank.domain.repository.CityRepository;
import com.wktechnology.bloodbank.domain.repository.StateRepository;
import com.wktechnology.bloodbank.domain.service.query.CityQueryService;
import com.wktechnology.bloodbank.domain.service.query.StateQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StateQueryServiceImpl implements StateQueryService {

    private final StateRepository repository;

    @Override
    public StateEntity findByAbbreviation(final String abbreviation) {
        return repository.findByAbbreviation(abbreviation)
                .orElseThrow(() -> new EntityNotFoundException("Estado não encontrada"));
    }
}
