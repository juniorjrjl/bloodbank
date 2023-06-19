package com.wktechnology.bloodbank.domain.repository;

import com.wktechnology.bloodbank.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByNameAndState_Abbreviation(final String name, final String abbreviation);

}
