package com.proyecto.RecruitAI.candidate.dto;

import com.proyecto.RecruitAI.candidate.model.Candidate;

public record CandidateResponse(
    String candidateId,
    String firstname,
    String lastname,
    String email,
    boolean isActive
) {
    public static CandidateResponse fromEntity(Candidate candidate) {
        return new CandidateResponse(
            candidate.getCandidateId(),
            candidate.getFirstname(),
            candidate.getLastname(),
            candidate.getAccount() != null ? candidate.getAccount().getEmail() : null,
            candidate.getAccount() != null && candidate.getAccount().isActive()
        );
    }
}
