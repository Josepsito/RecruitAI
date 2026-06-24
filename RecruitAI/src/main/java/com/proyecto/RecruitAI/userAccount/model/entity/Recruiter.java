package com.proyecto.RecruitAI.userAccount.model.entity;

import com.proyecto.RecruitAI.userAccount.model.UserAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "recruiter")
public class Recruiter extends UserAccount {

    private String companyName;
    private String companyWebsite;

    public Recruiter() {}

    public Recruiter(String fullName, String password, String phoneNumber, String email, LocalDate birthdate, String companyName, String companyWebsite) {
        super(fullName, password, phoneNumber, email, birthdate);
        this.companyName = companyName;
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }
}