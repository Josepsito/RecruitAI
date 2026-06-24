package com.proyecto.RecruitAI.userAccount.service;

import com.proyecto.RecruitAI.userAccount.dto.RegisterRecruiterRequest;
import com.proyecto.RecruitAI.userAccount.model.entity.Recruiter;
import com.proyecto.RecruitAI.userAccount.repository.RecruiterRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;

    public RecruiterService(RecruiterRepository recruiterRepository,
                            PasswordEncoder passwordEncoder) {
        this.recruiterRepository = recruiterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Recruiter register(RegisterRecruiterRequest request) {

        Recruiter recruiter = new Recruiter();

        recruiter.setFullName(request.fullName());
        recruiter.setEmail(request.email());
        recruiter.setPassword(passwordEncoder.encode(request.password()));
        recruiter.setPhoneNumber(request.phoneNumber());
        recruiter.setBirthdate(request.birthdate());

        recruiter.setCompanyName(request.companyName());
        recruiter.setCompanyWebsite(request.companyWebsite());

        return recruiterRepository.save(recruiter);
    }
}