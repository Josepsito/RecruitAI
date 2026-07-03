package com.proyecto.RecruitAI.jobOffert.controller;

import com.proyecto.RecruitAI.jobOffert.dto.JobOfferRequest;
import com.proyecto.RecruitAI.jobOffert.dto.JobOfferResponse;
import com.proyecto.RecruitAI.jobOffert.service.JobOfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-offers")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping
    public List<JobOfferResponse> getAllJobOffers() {
        return jobOfferService.getAllJobOffers();
    }

    @GetMapping("/{id}")
    public JobOfferResponse getJobOfferById(@PathVariable String id) {
        return jobOfferService.getJobOfferById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobOfferResponse createJobOffer(@Valid @RequestBody JobOfferRequest request) {
        return jobOfferService.createJobOffer(request);
    }

    @PutMapping("/{id}")
    public JobOfferResponse updateJobOffer(@PathVariable String id, @Valid @RequestBody JobOfferRequest request) {
        return jobOfferService.updateJobOffer(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJobOffer(@PathVariable String id) {
        jobOfferService.deleteJobOffer(id);
    }
}
