package com.wktechnology.bloodbank.domain.repository.impl;

import com.wktechnology.bloodbank.domain.dto.AgesBloodTypeDTO;
import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;
import com.wktechnology.bloodbank.domain.dto.IMCAndAgeDTO;
import com.wktechnology.bloodbank.domain.dto.IMCAndSexDTO;
import com.wktechnology.bloodbank.domain.entity.AddressEntity;
import com.wktechnology.bloodbank.domain.entity.AddressEntity_;
import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity;
import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity_;
import com.wktechnology.bloodbank.domain.entity.CandidateEntity;
import com.wktechnology.bloodbank.domain.entity.CandidateEntity_;
import com.wktechnology.bloodbank.domain.entity.CityEntity_;
import com.wktechnology.bloodbank.domain.entity.StateEntity_;
import com.wktechnology.bloodbank.domain.repository.CandidateCriteriaRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CandidateCriteriaRepositoryImpl implements CandidateCriteriaRepository {

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

    @Override
    public List<AgesBloodTypeDTO> agesAndBloodType() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(AgesBloodTypeDTO.class);
        var root = query.from(BloodTypeEntity.class);

        var joinCandidates = root.join(BloodTypeEntity_.candidates);

        query.select(criteriaBuilder.construct(AgesBloodTypeDTO.class, root.get(BloodTypeEntity_.name),
                joinCandidates.get(CandidateEntity_.birthdate)));
        query.orderBy(criteriaBuilder.asc(root.get(BloodTypeEntity_.name)));
        var typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<IMCAndSexDTO> imcAndSex() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(IMCAndSexDTO.class);
        var root = query.from(CandidateEntity.class);

        query.select(criteriaBuilder.construct(IMCAndSexDTO.class,
                root.get(CandidateEntity_.sex), root.get(CandidateEntity_.weight), root.get(CandidateEntity_.height)));
        query.orderBy(criteriaBuilder.asc(root.get(CandidateEntity_.sex)));
        var typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<IMCAndAgeDTO> imcAndAge() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(IMCAndAgeDTO.class);
        var root = query.from(CandidateEntity.class);

        query.select(criteriaBuilder.construct(IMCAndAgeDTO.class,
                root.get(CandidateEntity_.birthdate), root.get(CandidateEntity_.weight), root.get(CandidateEntity_.height)));
        query.orderBy(criteriaBuilder.desc(root.get(CandidateEntity_.birthdate)));
        var typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

}
