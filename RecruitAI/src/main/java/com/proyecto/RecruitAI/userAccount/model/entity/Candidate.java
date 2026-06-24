package com.proyecto.RecruitAI.userAccount.model.entity;

import com.proyecto.RecruitAI.userAccount.model.UserAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "candidate")
public class Candidate extends UserAccount {

    private String cvUrl;
    private String profession;

    public Candidate(String cvUrl, String profession) {
        this.cvUrl = cvUrl;
        this.profession = profession;
    }

    public Candidate(String fullName, String password, String phoneNumber, String email, LocalDate birthdate, String cvUrl, String profession) {
        super(fullName, password, phoneNumber, email, birthdate);
        this.cvUrl = cvUrl;
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }
}