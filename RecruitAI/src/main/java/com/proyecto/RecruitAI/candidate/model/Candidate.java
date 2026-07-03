package com.proyecto.RecruitAI.candidate.model;

import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.applicationJob.model.ApplicationJob;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @Column(name = "candidate_id")
    private String candidateId;
    private String firstname;
    private String lastname;


    @OneToOne(optional = false)
    @JoinColumn(name = "account_user_id")
    private Account account;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<ApplicationJob> applicationJobs = new ArrayList<>();

    public Candidate() {
    }

    @PrePersist
    public void prePersist() {
        if (candidateId == null) {
            candidateId = UUID.randomUUID().toString();
        }
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<ApplicationJob> getApplicationJobs() {
        return applicationJobs;
    }

    public void setApplicationJobs(List<ApplicationJob> applicationJobs) {
        this.applicationJobs = applicationJobs;
    }
}
