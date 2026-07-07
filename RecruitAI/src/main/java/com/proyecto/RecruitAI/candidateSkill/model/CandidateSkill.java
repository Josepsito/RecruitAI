package com.proyecto.RecruitAI.candidateSkill.model;

import com.proyecto.RecruitAI.candidate.model.Candidate;
import com.proyecto.RecruitAI.skill.model.Skill;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidate-skill")
public class CandidateSkill {

    @Id
    private String id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Skill skill;

    private Integer yearsExperience;

    private Double confidence; // 0-100

    private String source; // CV, LinkedIn, Manual

    public CandidateSkill() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}