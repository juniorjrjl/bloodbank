package com.wktechnology.bloodbank.domain.service;

import com.wktechnology.bloodbank.domain.dto.CandidateInfoBatchSaveDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CandidateService {

    void saveBatch(List<CandidateInfoBatchSaveDTO> candidates);

}
