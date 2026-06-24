package com.proyecto.RecruitAI.userAccount.repository;

import com.proyecto.RecruitAI.userAccount.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository
        extends JpaRepository<Candidate, String> {
}