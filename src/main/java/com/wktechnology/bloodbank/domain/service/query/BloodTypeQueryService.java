package com.wktechnology.bloodbank.domain.service.query;

import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity;

import java.util.List;

public interface BloodTypeQueryService {

    BloodTypeEntity findByName(final String name);

    List<BloodTypeEntity> findAll();

}
