package com.proyecto.RecruitAI.candidate.dto;

import jakarta.validation.constraints.NotBlank;

public record CandidateRequest(
    @NotBlank(message = "First name is required") String firstname,
    @NotBlank(message = "Last name is required") String lastname,
    @NotBlank(message = "Account user ID is required") String accountUserId
) {
}
