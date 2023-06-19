package com.wktechnology.bloodbank.domain.service;

import com.wktechnology.bloodbank.domain.entity.CityEntity;

import java.util.List;

public interface CityService {

    List<CityEntity> saveBatch(List<CityEntity> cities);

}
