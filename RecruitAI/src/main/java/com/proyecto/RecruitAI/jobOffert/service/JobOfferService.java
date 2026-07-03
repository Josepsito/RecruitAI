package com.proyecto.RecruitAI.jobOffert.service;

import com.proyecto.RecruitAI.company.model.Company;
import com.proyecto.RecruitAI.company.repository.CompanyRepository;
import com.proyecto.RecruitAI.jobOffert.dto.JobOfferRequest;
import com.proyecto.RecruitAI.jobOffert.dto.JobOfferResponse;
import com.proyecto.RecruitAI.jobOffert.model.JobOffer;
import com.proyecto.RecruitAI.jobOffert.repository.JobOfferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final CompanyRepository companyRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository, CompanyRepository companyRepository) {
        this.jobOfferRepository = jobOfferRepository;
        this.companyRepository = companyRepository;
    }

    public List<JobOfferResponse> getAllJobOffers() {
        return jobOfferRepository.findAll().stream()
                .map(JobOfferResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public JobOfferResponse getJobOfferById(String id) {
        JobOffer offer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job offer not found with id: " + id));
        return JobOfferResponse.fromEntity(offer);
    }

    public JobOfferResponse createJobOffer(JobOfferRequest request) {
        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + request.companyId()));

        JobOffer offer = new JobOffer();
        offer.setTitle(request.title());
        offer.setDescription(request.description());
        offer.setRequirements(request.requirements());
        offer.setResponsibilities(request.responsibilities());
        offer.setSalaryMin(request.salaryMin());
        offer.setSalaryMax(request.salaryMax());
        offer.setLocation(request.location());
        offer.setRemote(request.remote());
        offer.setExpirationDate(request.expirationDate());
        offer.setEmploymentType(request.employmentType());
        if (request.status() != null) {
            offer.setStatus(request.status());
        }
        offer.setCompany(company);

        JobOffer savedOffer = jobOfferRepository.save(offer);
        return JobOfferResponse.fromEntity(savedOffer);
    }

    public JobOfferResponse updateJobOffer(String id, JobOfferRequest request) {
        JobOffer offer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job offer not found with id: " + id));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + request.companyId()));

        offer.setTitle(request.title());
        offer.setDescription(request.description());
        offer.setRequirements(request.requirements());
        offer.setResponsibilities(request.responsibilities());
        offer.setSalaryMin(request.salaryMin());
        offer.setSalaryMax(request.salaryMax());
        offer.setLocation(request.location());
        offer.setRemote(request.remote());
        offer.setExpirationDate(request.expirationDate());
        offer.setEmploymentType(request.employmentType());
        if (request.status() != null) {
            offer.setStatus(request.status());
        }
        offer.setCompany(company);

        JobOffer updatedOffer = jobOfferRepository.save(offer);
        return JobOfferResponse.fromEntity(updatedOffer);
    }

    public void deleteJobOffer(String id) {
        JobOffer offer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job offer not found with id: " + id));
        jobOfferRepository.delete(offer);
    }
}
