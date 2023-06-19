package com.wktechnology.bloodbank.domain.service.query;

import com.wktechnology.bloodbank.domain.dto.BloodTypeAvengeAgeDTO;
import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;
import com.wktechnology.bloodbank.domain.dto.PercentObesePerSexDTO;

import java.util.List;

public interface CandidateQueryService {

    List<CandidatePerStateDTO> amountPerState();

    List<BloodTypeAvengeAgeDTO> avengeAgePerBloodType();

    List<PercentObesePerSexDTO> percentObesesPerSex();

}
