package com.wktechnology.bloodbank.domain.service.query;

import com.wktechnology.bloodbank.domain.entity.CityEntity;

public interface CityQueryService {

    CityEntity findByNameAndStateAbbreviation(final String name, final String abbreviation);

}
