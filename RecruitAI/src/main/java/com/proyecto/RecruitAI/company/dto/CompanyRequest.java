package com.proyecto.RecruitAI.company.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequest(
    @NotBlank(message = "Company name is required") String name,
    String website,
    String description,
    @NotBlank(message = "Account user ID is required") String accountUserId
) {
}
