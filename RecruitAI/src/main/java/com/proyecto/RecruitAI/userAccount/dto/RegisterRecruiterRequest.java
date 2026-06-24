package com.proyecto.RecruitAI.userAccount.dto;

import java.time.LocalDate;

public record RegisterRecruiterRequest(
        String fullName,
        String email,
        String password,
        String phoneNumber,
        LocalDate birthdate,
        String companyName,
        String companyWebsite
) {}