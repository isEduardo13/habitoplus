package com.habitoplus.habitoplusback.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class NotificationDTO {

    private Integer idProfile;
    @NotNull(message = "Message cannot be null")
    private String message;
    @NotNull(message = "Type cannot be null")
    private String type;
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;
    @NotNull(message = "isRead cannot be null")
    private Boolean isRead;
    
    public NotificationDTO() {

    }
    public NotificationDTO( int idProfile, String message, String type, LocalDateTime date, Boolean isRead) {
        this.idProfile = idProfile;
        this.message = message;
        this.type = type;
        this.date = date;
        this.isRead = isRead;
    }

    public int getIdProfile() {
        return idProfile;
    }
    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
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
