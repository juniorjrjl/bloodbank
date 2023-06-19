package com.wktechnology.bloodbank.api.controller;

import com.wktechnology.bloodbank.api.controller.request.CandidateSaveBatchRequest;
import com.wktechnology.bloodbank.domain.dto.CandidatePerStateDTO;
import com.wktechnology.bloodbank.domain.mapper.CandidateMapper;
import com.wktechnology.bloodbank.domain.service.CandidateService;
import com.wktechnology.bloodbank.domain.service.query.CandidateQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {

    private final CandidateService service;
    private final CandidateQueryService queryService;
    private final CandidateMapper mapper;

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void saveBatch(@RequestBody final List<CandidateSaveBatchRequest> request){
        var dto = request.stream().map(mapper::toDTO).toList();
        service.saveBatch(dto);
    }

    @GetMapping("/amount-per-state")
    public List<CandidatePerStateDTO> amountPerState(){
        return queryService.amountPerState();
    }

}
