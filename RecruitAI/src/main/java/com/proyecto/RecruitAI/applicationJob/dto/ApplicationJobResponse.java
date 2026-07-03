package com.proyecto.RecruitAI.applicationJob.dto;

import com.proyecto.RecruitAI.applicationJob.model.ApplicationJob;
import com.proyecto.RecruitAI.applicationJob.model.ApplicationStatus;
import java.time.LocalDateTime;

public record ApplicationJobResponse(
    String appjobId,
    String candidateId,
    String candidateFirstname,
    String candidateLastname,
    String jobOfferId,
    String jobOfferTitle,
    ApplicationStatus status,
    LocalDateTime appliedAt,
    LocalDateTime updatedAt,
    String resumeUrl,
    String coverLetter
) {
    public static ApplicationJobResponse fromEntity(ApplicationJob appJob) {
        return new ApplicationJobResponse(
            appJob.getAppjobId(),
            appJob.getCandidate() != null ? appJob.getCandidate().getCandidateId() : null,
            appJob.getCandidate() != null ? appJob.getCandidate().getFirstname() : null,
            appJob.getCandidate() != null ? appJob.getCandidate().getLastname() : null,
            appJob.getJobOffer() != null ? appJob.getJobOffer().getId() : null,
            appJob.getJobOffer() != null ? appJob.getJobOffer().getTitle() : null,
            appJob.getStatus(),
            appJob.getAppliedAt(),
            appJob.getUpdatedAt(),
            appJob.getResumeUrl(),
            appJob.getCoverLetter()
        );
    }
}
