package com.proyecto.RecruitAI.userAccount.controller;

import com.proyecto.RecruitAI.userAccount.dto.RegisterRecruiterRequest;
import com.proyecto.RecruitAI.userAccount.model.entity.Recruiter;
import com.proyecto.RecruitAI.userAccount.service.RecruiterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @PostMapping
    public Recruiter register(@RequestBody RegisterRecruiterRequest request) {
        return recruiterService.register(request);
    }
}