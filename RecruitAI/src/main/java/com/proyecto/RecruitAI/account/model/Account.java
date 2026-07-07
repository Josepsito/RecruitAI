package com.proyecto.RecruitAI.account.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(length = 36, name = "user_id")
    private String userId;

    private String password;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAccount typeAccount;

    @PrePersist
    public void prePersist() {
        if (userId == null) {
            userId = UUID.randomUUID().toString();
        }

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Account() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", typeAccount=" + typeAccount +
                '}';
    }
}
