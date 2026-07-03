package com.proyecto.RecruitAI.jobOffert.repository;

import com.proyecto.RecruitAI.jobOffert.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, String> {
}
