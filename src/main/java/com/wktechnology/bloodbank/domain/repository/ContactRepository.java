package com.wktechnology.bloodbank.domain.repository;

import com.wktechnology.bloodbank.domain.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
