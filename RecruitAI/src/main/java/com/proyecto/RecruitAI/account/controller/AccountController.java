package com.proyecto.RecruitAI.account.controller;

import com.proyecto.RecruitAI.account.dto.request.CreateAccountCandidateRequest;
import com.proyecto.RecruitAI.account.dto.request.CreateAccountCompanyRequest;
import com.proyecto.RecruitAI.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/candidate")
    public ResponseEntity<String> createNewCandidate(@RequestBody CreateAccountCandidateRequest request){

        accountService.createNewCandidate(request);

        return ResponseEntity.status(201).body("Creado Correctamente");
    }

    @PostMapping("/company")
    public ResponseEntity<String> createNewCompany(@RequestBody CreateAccountCompanyRequest request){

        accountService.createNewCompany(request);

        return ResponseEntity.status(201).body("Creado Correctamente");
    }

    @DeleteMapping("/{id}")
    public void deleteAccountByID(@PathVariable String id){
        accountService.deleteAccount(id);
    }

}
