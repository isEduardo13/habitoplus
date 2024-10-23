package com.habitoplus.habitoplusback.dto;

import java.time.LocalDateTime;

public class CommentWithProfileDTO {
    private String content;
    private LocalDateTime dateTime;
    private ProfileDTO profile;

    public CommentWithProfileDTO(ProfileDTO profile, String content, LocalDateTime dateTime ) {
        this.content = content;
        this.dateTime = dateTime;
        this.profile = profile;
    }

    public CommentWithProfileDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    
    
}
