package com.habitoplus.habitoplusback.dto;

import java.util.Date;

import com.habitoplus.habitoplusback.enums.Status;

public class RequestWithProfileDto {
    private Date dateRequest;
    private Status status;
    private ProfileDto profile;
    
    public RequestWithProfileDto(ProfileDto profile, Date dateRequest, Status status) {
        this.dateRequest = dateRequest;
        this.status = status;
        this.profile = profile;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

}
