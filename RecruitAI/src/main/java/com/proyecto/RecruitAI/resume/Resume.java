package com.proyecto.RecruitAI.resume;

import com.proyecto.RecruitAI.userAccount.model.UserAccount;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    private String resumeId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    private String cvUrl;

    @Lob
    private String rawText;

    private String embeddingId;

    private LocalDateTime createdAt;


    public Resume(UserAccount user, String cvUrl, String rawText, String embeddingId) {
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
}
