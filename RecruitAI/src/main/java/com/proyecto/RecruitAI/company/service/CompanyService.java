package com.proyecto.RecruitAI.company.service;

import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.account.model.TypeAccount;
import com.proyecto.RecruitAI.account.repository.AccountRepository;
import com.proyecto.RecruitAI.company.dto.CompanyRequest;
import com.proyecto.RecruitAI.company.dto.CompanyResponse;
import com.proyecto.RecruitAI.company.model.Company;
import com.proyecto.RecruitAI.company.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AccountRepository accountRepository;

    public CompanyService(CompanyRepository companyRepository, AccountRepository accountRepository) {
        this.companyRepository = companyRepository;
        this.accountRepository = accountRepository;
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CompanyResponse getCompanyById(String id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));
        return CompanyResponse.fromEntity(company);
    }

    public CompanyResponse createCompany(CompanyRequest request) {
        Account account = accountRepository.findById(request.accountUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + request.accountUserId()));

        if (account.getTypeAccount() != TypeAccount.COMPANY) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type must be COMPANY");
        }

        if (companyRepository.existsByAccountUserId(request.accountUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An account can only be linked to one company profile");
        }

        Company company = new Company();
        company.setName(request.name());
        company.setWebsite(request.website());
        company.setDescription(request.description());
        company.setAccount(account);

        Company savedCompany = companyRepository.save(company);
        return CompanyResponse.fromEntity(savedCompany);
    }

    public CompanyResponse updateCompany(String id, CompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));

        // If changing account, perform validation
        if (!company.getAccount().getUserId().equals(request.accountUserId())) {
            Account newAccount = accountRepository.findById(request.accountUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + request.accountUserId()));

            if (newAccount.getTypeAccount() != TypeAccount.COMPANY) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type must be COMPANY");
            }

            if (companyRepository.existsByAccountUserIdAndCompanyIdNot(request.accountUserId(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An account can only be linked to one company profile");
            }
            company.setAccount(newAccount);
        }

        company.setName(request.name());
        company.setWebsite(request.website());
        company.setDescription(request.description());

        Company updatedCompany = companyRepository.save(company);
        return CompanyResponse.fromEntity(updatedCompany);
    }

    public void deleteCompany(String id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));
        companyRepository.delete(company);
    }
}
