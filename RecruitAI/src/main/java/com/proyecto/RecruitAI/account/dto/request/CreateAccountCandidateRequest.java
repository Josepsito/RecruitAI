package com.proyecto.RecruitAI.account.dto.request;

public record CreateAccountCandidateRequest(
        String email,
        String password,
        String profilePhoto,
        String firstname,
        String lastname,
        String phone,
        String address,
        String city,
        String country,
        String resumeUrl,
        String profileUrl,
        String portfolioUrl,
        Integer yearsExperience,
        Double expectedSalary
) {



}
