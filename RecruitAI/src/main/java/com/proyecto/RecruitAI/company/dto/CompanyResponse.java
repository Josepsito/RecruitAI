package com.proyecto.RecruitAI.company.dto;

import com.proyecto.RecruitAI.company.model.Company;

public record CompanyResponse(
    String companyId,
    String name,
    String website,
    String description,
    String email,
    boolean isActive
) {
    public static CompanyResponse fromEntity(Company company) {
        return new CompanyResponse(
            company.getCompanyId(),
            company.getName(),
            company.getWebsite(),
            company.getDescription(),
            company.getAccount() != null ? company.getAccount().getEmail() : null,
            company.getAccount() != null && company.getAccount().isActive()
        );
    }
}
