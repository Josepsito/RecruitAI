package com.proyecto.RecruitAI.candidate.controller;

import com.proyecto.RecruitAI.candidate.dto.CandidateRequest;
import com.proyecto.RecruitAI.candidate.dto.CandidateResponse;
import com.proyecto.RecruitAI.candidate.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateResponse> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public CandidateResponse getCandidateById(@PathVariable String id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateResponse createCandidate(@Valid @RequestBody CandidateRequest request) {
        return candidateService.createCandidate(request);
    }

    @PutMapping("/{id}")
    public CandidateResponse updateCandidate(@PathVariable String id, @Valid @RequestBody CandidateRequest request) {
        return candidateService.updateCandidate(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidate(@PathVariable String id) {
        candidateService.deleteCandidate(id);
    }
}
