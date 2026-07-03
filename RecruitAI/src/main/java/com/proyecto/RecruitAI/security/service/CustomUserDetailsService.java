package com.proyecto.RecruitAI.security.service;

import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.account.repository.AccountRepository;
import com.proyecto.RecruitAI.candidate.model.Candidate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        Account user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));



        String role;

        if (user.getTypeAccount().toString().equals("CANDIDATE")) {
            role = "CANDIDATE";
        } else if (user.getTypeAccount().toString().equals("COMPANY")) {
            role = "COMPANY";
        } else {
            throw new RuntimeException("Unknown user type");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}