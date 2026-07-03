package com.proyecto.RecruitAI.applicationJob.repository;

import com.proyecto.RecruitAI.applicationJob.model.ApplicationJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationJobRepository extends JpaRepository<ApplicationJob, String> {
    boolean existsByCandidateCandidateIdAndJobOfferId(String candidateId, String jobOfferId);
    boolean existsByCandidateCandidateIdAndJobOfferIdAndAppjobIdNot(String candidateId, String jobOfferId, String appjobId);
}
