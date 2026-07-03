package com.proyecto.RecruitAI.jobOffert.dto;

import com.proyecto.RecruitAI.jobOffert.model.JobOffer;
import com.proyecto.RecruitAI.jobOffert.model.JobOffer.EmploymentType;
import com.proyecto.RecruitAI.jobOffert.model.JobOffer.JobOfferStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record JobOfferResponse(
    String id,
    String title,
    String description,
    String requirements,
    String responsibilities,
    Double salaryMin,
    Double salaryMax,
    String location,
    boolean remote,
    LocalDate expirationDate,
    EmploymentType employmentType,
    JobOfferStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String companyId,
    String companyName
) {
    public static JobOfferResponse fromEntity(JobOffer offer) {
        return new JobOfferResponse(
            offer.getId(),
            offer.getTitle(),
            offer.getDescription(),
            offer.getRequirements(),
            offer.getResponsibilities(),
            offer.getSalaryMin(),
            offer.getSalaryMax(),
            offer.getLocation(),
            offer.isRemote(),
            offer.getExpirationDate(),
            offer.getEmploymentType(),
            offer.getStatus(),
            offer.getCreatedAt(),
            offer.getUpdatedAt(),
            offer.getCompany() != null ? offer.getCompany().getCompanyId() : null,
            offer.getCompany() != null ? offer.getCompany().getName() : null
        );
    }
}
