package com.proyecto.RecruitAI.company.repository;

import com.proyecto.RecruitAI.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    boolean existsByAccountUserId(String userId);
    boolean existsByAccountUserIdAndCompanyIdNot(String userId, String companyId);
}
