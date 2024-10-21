package com.habitoplus.habitoplusback.dto;

import java.time.LocalDateTime;

public class NotificationDTO {

    private Integer idProfile;
    private String message;
    private String type;
    private LocalDateTime date;
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
