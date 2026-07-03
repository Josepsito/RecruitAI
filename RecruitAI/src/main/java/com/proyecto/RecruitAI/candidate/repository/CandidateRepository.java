package com.proyecto.RecruitAI.candidate.repository;

import com.proyecto.RecruitAI.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
    boolean existsByAccountUserId(String userId);
    boolean existsByAccountUserIdAndCandidateIdNot(String userId, String candidateId);
}
