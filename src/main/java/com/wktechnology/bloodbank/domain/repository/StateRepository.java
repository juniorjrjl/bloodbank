package com.wktechnology.bloodbank.domain.repository;

import com.wktechnology.bloodbank.domain.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {

    Optional<StateEntity> findByAbbreviation(final String abbreviation);

}
