package com.proyecto.RecruitAI.userAccount.repository;

import com.proyecto.RecruitAI.userAccount.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount,String> {
    Optional<UserAccount> findByEmail(String email);
}
