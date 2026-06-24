package com.proyecto.RecruitAI.security.service;

import com.proyecto.RecruitAI.userAccount.model.UserAccount;
import com.proyecto.RecruitAI.userAccount.model.entity.Candidate;
import com.proyecto.RecruitAI.userAccount.model.entity.Recruiter;
import com.proyecto.RecruitAI.userAccount.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserAccount user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String role;

        if (user instanceof Recruiter) {
            role = "RECRUITER";
        } else if (user instanceof Candidate) {
            role = "CANDIDATE";
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