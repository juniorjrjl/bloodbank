package com.wktechnology.bloodbank.domain.service.query.impl;

import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;
import com.wktechnology.bloodbank.domain.entity.AddressEntity;
import com.wktechnology.bloodbank.domain.entity.AddressEntity_;
import com.wktechnology.bloodbank.domain.entity.CityEntity_;
import com.wktechnology.bloodbank.domain.entity.StateEntity_;
import com.wktechnology.bloodbank.domain.service.query.CandidateQueryService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CandidateQueryServiceImpl implements CandidateQueryService {

    private final EntityManager entityManager;

    @Override
    public List<CandidatePerStateDTO> amountPerState() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(CandidatePerStateDTO.class);
        var root = query.from(AddressEntity.class);

        var joinCities = root.join(AddressEntity_.city);
        var joinStates = joinCities.join(CityEntity_.state);

        query.select(criteriaBuilder.construct(CandidatePerStateDTO.class,
                joinStates.get(StateEntity_.ABBREVIATION), criteriaBuilder.count(joinStates.get(StateEntity_.ABBREVIATION))));
        query.groupBy(joinStates.get(StateEntity_.ABBREVIATION));
        query.orderBy(criteriaBuilder.asc(joinStates.get(StateEntity_.ABBREVIATION)));
        var typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

}
