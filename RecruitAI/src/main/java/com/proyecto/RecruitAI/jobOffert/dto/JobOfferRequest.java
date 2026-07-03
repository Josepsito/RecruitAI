package com.proyecto.RecruitAI.jobOffert.dto;

import com.proyecto.RecruitAI.jobOffert.model.JobOffer.EmploymentType;
import com.proyecto.RecruitAI.jobOffert.model.JobOffer.JobOfferStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record JobOfferRequest(
    @NotBlank(message = "Title is required") String title,
    String description,
    String requirements,
    String responsibilities,
    Double salaryMin,
    Double salaryMax,
    String location,
    boolean remote,
    LocalDate expirationDate,
    @NotNull(message = "Employment type is required") EmploymentType employmentType,
    JobOfferStatus status,
    @NotBlank(message = "Company ID is required") String companyId
) {
}
