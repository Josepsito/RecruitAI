package com.proyecto.RecruitAI.resume.model;

import com.proyecto.RecruitAI.account.model.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @Column(name = "resume_id")
    private String resumeId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Account user;

    private String cvUrl;

    @Lob
    private String rawText;

    private String embeddingId;

    private LocalDateTime createdAt;


    public Resume(Account user, String cvUrl, String rawText, String embeddingId) {
        this.user = user;
        this.cvUrl = cvUrl;
        this.rawText = rawText;
        this.embeddingId = embeddingId;
    }

    @PrePersist
    private void prePersist() {
        if (resumeId == null) {
            resumeId = UUID.randomUUID().toString();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getEmbeddingId() {
        return embeddingId;
    }

    public void setEmbeddingId(String embeddingId) {
        this.embeddingId = embeddingId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
