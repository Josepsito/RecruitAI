package com.proyecto.RecruitAI.company.controller;

import com.proyecto.RecruitAI.account.dto.request.CreateAccountCompanyRequest;
import com.proyecto.RecruitAI.company.dto.CompanyRequest;
import com.proyecto.RecruitAI.company.dto.CompanyResponse;
import com.proyecto.RecruitAI.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable String id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@Valid @RequestBody CreateAccountCompanyRequest request) {
        companyService.createCompany(request);
        return ResponseEntity.status(201).body("Compañia creada correctamente");
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable String id, @Valid @RequestBody CompanyRequest request) {
        return companyService.updateCompany(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable String id) {
        companyService.deleteCompany(id);
    }
}
