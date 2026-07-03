package com.proyecto.RecruitAI.company.model;

import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.jobOffert.model.JobOffer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "company")
public class Company {

    @Id
    private String companyId;
    private String name;
    private String website;
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_user_id")
    private Account account;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<JobOffer> jobOffers = new ArrayList<>();

    public Company() {
    }

    @PrePersist
    public void prePersist() {
        if (companyId == null) {
            companyId = UUID.randomUUID().toString();
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

}