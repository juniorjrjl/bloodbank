package com.wktechnology.bloodbank.domain.service.impl;

import com.wktechnology.bloodbank.domain.dto.CandidateInfoBatchSaveDTO;
import com.wktechnology.bloodbank.domain.entity.CandidateEntity;
import com.wktechnology.bloodbank.domain.entity.CityEntity;
import com.wktechnology.bloodbank.domain.entity.StateEntity;
import com.wktechnology.bloodbank.domain.mapper.CandidateMapper;
import com.wktechnology.bloodbank.domain.repository.CandidateRepository;
import com.wktechnology.bloodbank.domain.service.CandidateService;
import com.wktechnology.bloodbank.domain.service.CityService;
import com.wktechnology.bloodbank.domain.service.StateService;
import com.wktechnology.bloodbank.domain.service.query.BloodTypeQueryService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository repository;
    private final StateService stateService;
    private final CityService cityService;
    private final BloodTypeQueryService bloodTypeQueryService;
    private final CandidateMapper candidateMapper;

    @Override
    @Async
    @Transactional
    public void saveBatch(final List<CandidateInfoBatchSaveDTO> candidates){
        var states = candidates.stream().map(c -> {
            var state = new StateEntity();
            state.setAbbreviation(c.state());
            return state;
        }).distinct().toList();
        stateService.saveBatch(states);

        var cities = candidates.stream().map(c -> {
            var city = new CityEntity();
            city.setName(c.city());
            city.getState().setAbbreviation(c.state());
            return city;
        }).distinct().toList();
        var storedCities = cityService.saveBatch(cities);

        List<CandidateEntity> entities = new ArrayList<>();
        for(CandidateInfoBatchSaveDTO candidate: candidates){
            var selectedCity = storedCities.stream().filter(c -> c.getName().equals(candidate.city()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(""));
            CandidateEntity entity = candidateMapper.build(candidate);
            entity.setBloodType(bloodTypeQueryService.findByName(candidate.bloodType()));
            entity.getAddresses().forEach(a -> a.setCity(selectedCity));
            entities.add(entity);
        }
        repository.saveAll(entities);
    }

}
