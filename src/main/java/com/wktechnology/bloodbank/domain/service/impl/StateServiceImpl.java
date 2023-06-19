package com.wktechnology.bloodbank.domain.service.impl;

import com.wktechnology.bloodbank.domain.entity.StateEntity;
import com.wktechnology.bloodbank.domain.repository.StateRepository;
import com.wktechnology.bloodbank.domain.service.StateService;
import com.wktechnology.bloodbank.domain.service.query.StateQueryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final StateRepository repository;
    private final StateQueryService queryService;

    @Transactional
    @Override
    public List<StateEntity> saveBatch(final List<StateEntity> states){
        List<StateEntity> alreadyStored = new ArrayList<>();
        List<StateEntity> toPersist = new ArrayList<>();

        states.forEach(s -> {
            try {
                var stored = queryService.findByAbbreviation(s.getAbbreviation());
                alreadyStored.add(stored);
            }catch (EntityNotFoundException ex){
                toPersist.add(s);
            }
        });
        alreadyStored.addAll(repository.saveAll(toPersist));
        return alreadyStored;
    }

}
