package com.proyecto.RecruitAI.applicationJob.dto;

import com.proyecto.RecruitAI.applicationJob.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;

public record ApplicationJobRequest(
    @NotBlank(message = "Candidate ID is required") String candidateId,
    @NotBlank(message = "Job offer ID is required") String jobOfferId,
    ApplicationStatus status,
    String resumeUrl,
    String coverLetter
) {
}
