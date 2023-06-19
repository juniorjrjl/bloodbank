package com.wktechnology.bloodbank.domain.service.query;

import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity;

public interface BloodTypeQueryService {

    BloodTypeEntity findByName(final String name);

}
