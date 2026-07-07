package com.proyecto.RecruitAI.candidate.service;

import com.proyecto.RecruitAI.account.dto.request.CreateAccountCandidateRequest;
import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.account.model.TypeAccount;
import com.proyecto.RecruitAI.account.repository.AccountRepository;
import com.proyecto.RecruitAI.account.service.AccountService;
import com.proyecto.RecruitAI.candidate.dto.CandidateRequest;
import com.proyecto.RecruitAI.candidate.dto.CandidateResponse;
import com.proyecto.RecruitAI.candidate.model.Candidate;
import com.proyecto.RecruitAI.candidate.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public CandidateService(CandidateRepository candidateRepository, AccountRepository accountRepository, AccountService accountService) {
        this.candidateRepository = candidateRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(CandidateResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CandidateResponse getCandidateById(String id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with id: " + id));
        return CandidateResponse.fromEntity(candidate);
    }

    public void createCandidate(CreateAccountCandidateRequest request) {
        accountService.createNewCandidate(request);
    }

    public CandidateResponse updateCandidate(String id, CandidateRequest request) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with id: " + id));

        if (!candidate.getAccount().getUserId().equals(request.accountUserId())) {
            Account newAccount = accountRepository.findById(request.accountUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + request.accountUserId()));

            if (newAccount.getTypeAccount() != TypeAccount.CANDIDATE) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type must be CANDIDATE");
            }

            if (candidateRepository.existsByAccountUserIdAndCandidateIdNot(request.accountUserId(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An account can only be linked to one candidate profile");
            }
            candidate.setAccount(newAccount);
        }

        candidate.setFirstname(request.firstname());
        candidate.setLastname(request.lastname());

        Candidate updatedCandidate = candidateRepository.save(candidate);
        return CandidateResponse.fromEntity(updatedCandidate);

    }

    @Transactional
    public void deleteCandidate(String id) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Candidate not found"));

        Account account = candidate.getAccount();

        candidateRepository.delete(candidate);


        accountRepository.delete(account);
    }



}
