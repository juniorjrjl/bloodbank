package com.wktechnology.bloodbank.domain.service.query;

import com.wktechnology.bloodbank.domain.entity.StateEntity;

public interface StateQueryService {

    StateEntity findByAbbreviation(final String abbreviation);

}
