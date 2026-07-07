package com.proyecto.RecruitAI.candidate.model;

import com.proyecto.RecruitAI.account.model.Account;
import com.proyecto.RecruitAI.applicationJob.model.ApplicationJob;
import com.proyecto.RecruitAI.candidate.model.enums.Availability;
import com.proyecto.RecruitAI.candidate.model.enums.EducationLevel;
import com.proyecto.RecruitAI.candidate.model.enums.WorkModality;
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

    private String phone;
    private String address;
    private String city;
    private String country;
    private String resumeUrl;
    private String profileUrl;
    private String portfolioUrl;

    private Integer yearsExperience;
    private Double expectedSalary;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private WorkModality preferredWorkModality;

    @Enumerated(EnumType.STRING)
    private Availability availability;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public Double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public WorkModality getPreferredWorkModality() {
        return preferredWorkModality;
    }

    public void setPreferredWorkModality(WorkModality preferredWorkModality) {
        this.preferredWorkModality = preferredWorkModality;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId='" + candidateId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", account=" + account +
                ", applicationJobs=" + applicationJobs +
                '}';
    }
}
