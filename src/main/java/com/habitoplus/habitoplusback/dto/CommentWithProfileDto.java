package com.habitoplus.habitoplusback.Dto;

import java.time.LocalDateTime;

public class CommentWithProfileDto {
    private String content;
    private LocalDateTime dateTime;
    private ProfileDto profile;

    public CommentWithProfileDto(ProfileDto profile, String content, LocalDateTime dateTime ) {
        this.content = content;
        this.dateTime = dateTime;
        this.profile = profile;
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

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    
    
}
