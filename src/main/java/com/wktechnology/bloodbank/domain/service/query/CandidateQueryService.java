package com.wktechnology.bloodbank.domain.service.query;

import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;

import java.util.List;

public interface CandidateQueryService {

    List<CandidatePerStateDTO> amountPerState();

}
