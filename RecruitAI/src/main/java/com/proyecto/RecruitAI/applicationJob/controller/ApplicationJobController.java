package com.proyecto.RecruitAI.applicationJob.controller;

import com.proyecto.RecruitAI.applicationJob.dto.ApplicationJobRequest;
import com.proyecto.RecruitAI.applicationJob.dto.ApplicationJobResponse;
import com.proyecto.RecruitAI.applicationJob.service.ApplicationJobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application-jobs")
public class ApplicationJobController {

    private final ApplicationJobService applicationJobService;

    public ApplicationJobController(ApplicationJobService applicationJobService) {
        this.applicationJobService = applicationJobService;
    }

    @GetMapping
    public List<ApplicationJobResponse> getAllApplicationJobs() {
        return applicationJobService.getAllApplicationJobs();
    }

    @GetMapping("/{id}")
    public ApplicationJobResponse getApplicationJobById(@PathVariable String id) {
        return applicationJobService.getApplicationJobById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationJobResponse createApplicationJob(@Valid @RequestBody ApplicationJobRequest request) {
        return applicationJobService.createApplicationJob(request);
    }

    @PutMapping("/{id}")
    public ApplicationJobResponse updateApplicationJob(@PathVariable String id, @Valid @RequestBody ApplicationJobRequest request) {
        return applicationJobService.updateApplicationJob(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplicationJob(@PathVariable String id) {
        applicationJobService.deleteApplicationJob(id);
    }
}
