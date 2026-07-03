package com.proyecto.RecruitAI.applicationJob.service;

import com.proyecto.RecruitAI.applicationJob.dto.ApplicationJobRequest;
import com.proyecto.RecruitAI.applicationJob.dto.ApplicationJobResponse;
import com.proyecto.RecruitAI.applicationJob.model.ApplicationJob;
import com.proyecto.RecruitAI.applicationJob.repository.ApplicationJobRepository;
import com.proyecto.RecruitAI.candidate.model.Candidate;
import com.proyecto.RecruitAI.candidate.repository.CandidateRepository;
import com.proyecto.RecruitAI.jobOffert.model.JobOffer;
import com.proyecto.RecruitAI.jobOffert.repository.JobOfferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationJobService {

    private final ApplicationJobRepository applicationJobRepository;
    private final CandidateRepository candidateRepository;
    private final JobOfferRepository jobOfferRepository;

    public ApplicationJobService(ApplicationJobRepository applicationJobRepository,
                                  CandidateRepository candidateRepository,
                                  JobOfferRepository jobOfferRepository) {
        this.applicationJobRepository = applicationJobRepository;
        this.candidateRepository = candidateRepository;
        this.jobOfferRepository = jobOfferRepository;
    }

    public List<ApplicationJobResponse> getAllApplicationJobs() {
        return applicationJobRepository.findAll().stream()
                .map(ApplicationJobResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ApplicationJobResponse getApplicationJobById(String id) {
        ApplicationJob appJob = applicationJobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application job not found with id: " + id));
        return ApplicationJobResponse.fromEntity(appJob);
    }

    public ApplicationJobResponse createApplicationJob(ApplicationJobRequest request) {
        Candidate candidate = candidateRepository.findById(request.candidateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with id: " + request.candidateId()));

        JobOffer jobOffer = jobOfferRepository.findById(request.jobOfferId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job offer not found with id: " + request.jobOfferId()));

        if (applicationJobRepository.existsByCandidateCandidateIdAndJobOfferId(request.candidateId(), request.jobOfferId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Candidate has already applied to this job offer");
        }

        ApplicationJob appJob = new ApplicationJob();
        appJob.setCandidate(candidate);
        appJob.setJobOffer(jobOffer);
        if (request.status() != null) {
            appJob.setStatus(request.status());
        }
        appJob.setResumeUrl(request.resumeUrl());
        appJob.setCoverLetter(request.coverLetter());

        ApplicationJob savedAppJob = applicationJobRepository.save(appJob);
        return ApplicationJobResponse.fromEntity(savedAppJob);
    }

    public ApplicationJobResponse updateApplicationJob(String id, ApplicationJobRequest request) {
        ApplicationJob appJob = applicationJobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application job not found with id: " + id));

        Candidate candidate = candidateRepository.findById(request.candidateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found with id: " + request.candidateId()));

        JobOffer jobOffer = jobOfferRepository.findById(request.jobOfferId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job offer not found with id: " + request.jobOfferId()));

        // If candidate or job offer changed, validate uniqueness constraint
        if (!appJob.getCandidate().getCandidateId().equals(request.candidateId()) ||
                !appJob.getJobOffer().getId().equals(request.jobOfferId())) {
            if (applicationJobRepository.existsByCandidateCandidateIdAndJobOfferIdAndAppjobIdNot(request.candidateId(), request.jobOfferId(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Candidate has already applied to this job offer");
            }
        }

        appJob.setCandidate(candidate);
        appJob.setJobOffer(jobOffer);
        if (request.status() != null) {
            appJob.setStatus(request.status());
        }
        appJob.setResumeUrl(request.resumeUrl());
        appJob.setCoverLetter(request.coverLetter());

        ApplicationJob updatedAppJob = applicationJobRepository.save(appJob);
        return ApplicationJobResponse.fromEntity(updatedAppJob);
    }

    public void deleteApplicationJob(String id) {
        ApplicationJob appJob = applicationJobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application job not found with id: " + id));
        applicationJobRepository.delete(appJob);
    }
}
