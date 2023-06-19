package com.wktechnology.bloodbank.domain.repository;

import com.wktechnology.bloodbank.domain.entity.BloodTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodTypeRepository extends JpaRepository<BloodTypeEntity, Long> {

    Optional<BloodTypeEntity> findByName(final String name);

}
