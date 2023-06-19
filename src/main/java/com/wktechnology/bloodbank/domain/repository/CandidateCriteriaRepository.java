package com.wktechnology.bloodbank.domain.repository;

import com.wktechnology.bloodbank.domain.dto.AgesBloodTypeDTO;
import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;
import com.wktechnology.bloodbank.domain.dto.IMCAndSexDTO;
import com.wktechnology.bloodbank.domain.dto.IMCAndAgeDTO;

import java.util.List;

public interface CandidateCriteriaRepository {

    List<CandidatePerStateDTO> amountPerState();

    List<AgesBloodTypeDTO> agesAndBloodType();

    List<IMCAndSexDTO> imcAndSex();

    List<IMCAndAgeDTO> imcAndAge();

}
