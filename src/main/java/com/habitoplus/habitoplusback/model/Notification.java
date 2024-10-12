package com.habitoplus.habitoplusback.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false) 
    @NotNull(message = "Profile cannot be null")
    private Profile profile; 

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Message cannot be blank")
    @Size(max = 500, message = "Message must not exceed 500 characters")
    private String message;

    @Column(name = "type", length = 50, nullable = false)
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @Column(name = "isRead", columnDefinition = "BIT")
    @NotNull(message = "Read status cannot be null")
    private Boolean isRead; 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}
