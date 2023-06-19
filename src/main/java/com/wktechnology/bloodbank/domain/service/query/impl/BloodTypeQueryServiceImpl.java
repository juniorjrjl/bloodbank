package com.wktechnology.bloodbank.domain.service.query.impl;

import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity;
import com.wktechnology.bloodbank.domain.repository.BloodTypeRepository;
import com.wktechnology.bloodbank.domain.service.query.BloodTypeQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BloodTypeQueryServiceImpl implements BloodTypeQueryService {

    private final BloodTypeRepository repository;

    @Override
    public BloodTypeEntity findByName(final String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Tipo sanguíneo não encontrado"));
    }

}
