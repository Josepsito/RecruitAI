package com.proyecto.RecruitAI.candidate.service;

import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.account.model.TypeAccount;
import com.proyecto.RecruitAI.account.repository.AccountRepository;
import com.proyecto.RecruitAI.candidate.dto.CandidateRequest;
import com.proyecto.RecruitAI.candidate.dto.CandidateResponse;
import com.proyecto.RecruitAI.candidate.model.Candidate;
import com.proyecto.RecruitAI.candidate.repository.CandidateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final AccountRepository accountRepository;

    public CandidateService(CandidateRepository candidateRepository, AccountRepository accountRepository) {
        this.candidateRepository = candidateRepository;
        this.accountRepository = accountRepository;
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

    public CandidateResponse createCandidate(CandidateRequest request) {
        Account account = accountRepository.findById(request.accountUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id: " + request.accountUserId()));

        if (account.getTypeAccount() != TypeAccount.CANDIDATE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type must be CANDIDATE");
        }

        if (candidateRepository.existsByAccountUserId(request.accountUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An account can only be linked to one candidate profile");
        }

        Candidate candidate = new Candidate();
        candidate.setFirstname(request.firstname());
        candidate.setLastname(request.lastname());
        candidate.setAccount(account);

        Candidate savedCandidate = candidateRepository.save(candidate);
        return CandidateResponse.fromEntity(savedCandidate);
    }

    public CandidateResponse updateCandidate(String id, CandidateRequest request) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with id: " + id));

        // If changing account, perform validation
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

    public void deleteCandidate(String id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with id: " + id));
        candidateRepository.delete(candidate);
    }
}
