package com.proyecto.RecruitAI.account.dto.request;

public record CreateAccountCompanyRequest(
        String email,
        String password,
        String profilePhoto,
        String name,
        String website,
        String description
) {
}
