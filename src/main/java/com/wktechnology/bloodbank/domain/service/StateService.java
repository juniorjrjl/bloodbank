package com.wktechnology.bloodbank.domain.service;

import com.wktechnology.bloodbank.domain.entity.StateEntity;

import java.util.List;

public interface StateService {
    List<StateEntity> saveBatch(List<StateEntity> states);

}
