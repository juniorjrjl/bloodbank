package com.wktechnology.bloodbank.domain.service.impl;

import com.wktechnology.bloodbank.domain.entity.CityEntity;
import com.wktechnology.bloodbank.domain.repository.CityRepository;
import com.wktechnology.bloodbank.domain.service.CityService;
import com.wktechnology.bloodbank.domain.service.query.CityQueryService;
import com.wktechnology.bloodbank.domain.service.query.StateQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final CityQueryService queryService;
    private final StateQueryService stateQueryService;

    @Transactional
    @Override
    public List<CityEntity> saveBatch(final List<CityEntity> cities){
        List<CityEntity> alreadyStored = new ArrayList<>();
        List<CityEntity> toPersist = new ArrayList<>();

        for (CityEntity city : cities) {
            CityEntity nonStored = new CityEntity();
            try {
                var stored = queryService.findByNameAndStateAbbreviation(city.getName(), city.getState().getAbbreviation());
                alreadyStored.add(stored);
            } catch (EntityNotFoundException ex) {
                nonStored.setName(city.getName());
            }
            nonStored.setState(stateQueryService.findByAbbreviation(city.getState().getAbbreviation()));
            toPersist.add(nonStored);
        }
        alreadyStored.addAll(repository.saveAll(toPersist));
        return alreadyStored;
    }

}
