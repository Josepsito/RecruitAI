package com.proyecto.RecruitAI.userAccount.repository;

import com.proyecto.RecruitAI.userAccount.model.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository
        extends JpaRepository<Recruiter, String> {
}