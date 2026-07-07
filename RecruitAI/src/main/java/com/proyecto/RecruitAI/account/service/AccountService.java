package com.proyecto.RecruitAI.account.service;

import com.proyecto.RecruitAI.account.dto.request.CreateAccountCandidateRequest;
import com.proyecto.RecruitAI.account.dto.request.CreateAccountCompanyRequest;
import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.account.model.TypeAccount;
import com.proyecto.RecruitAI.account.repository.AccountRepository;
import com.proyecto.RecruitAI.candidate.model.Candidate;
import com.proyecto.RecruitAI.candidate.repository.CandidateRepository;
import com.proyecto.RecruitAI.company.model.Company;
import com.proyecto.RecruitAI.company.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public AccountService(AccountRepository accountRepository, CandidateRepository candidateRepository, PasswordEncoder passwordEncoder, CompanyRepository companyRepository) {
        this.accountRepository = accountRepository;
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void createNewCandidate(CreateAccountCandidateRequest request) {
        Account account = new Account();
        account.setTypeAccount(TypeAccount.CANDIDATE);
        account.setEmail(request.email());
        account.setPassword(passwordEncoder.encode(request.password()));
        account.setProfilePhoto(request.profilePhoto());

        accountRepository.save(account);

        Candidate candidate = new Candidate();
        candidate.setAccount(account);
        candidate.setFirstname(request.firstname());
        candidate.setLastname(request.lastname());
        candidate.setCity(request.city());
        candidate.setAddress(request.address());
        candidate.setPhone(request.phone());
        candidate.setCountry(request.country());
        candidate.setResumeUrl(request.resumeUrl());
        candidate.setProfileUrl(request.profileUrl());
        candidate.setPortfolioUrl(request.portfolioUrl());
        candidate.setYearsExperience(request.yearsExperience());
        candidate.setExpectedSalary(request.expectedSalary());

        candidateRepository.save(candidate);

    }

    @Transactional
    public void createNewCompany(CreateAccountCompanyRequest request) {

        Account account = new Account();
        account.setTypeAccount(TypeAccount.COMPANY);
        account.setEmail(request.email());
        account.setPassword(passwordEncoder.encode(request.password()));


        accountRepository.save(account);

        Company company = new Company();
        company.setAccount(account);
        company.setDescription(request.description());
        company.setName(request.name());
        company.setWebsite(request.website());


        companyRepository.save(company);

    }

    @Transactional
    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }

}
