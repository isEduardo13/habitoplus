package com.habitoplus.habitoplusback.dto;

import java.util.Date;

import com.habitoplus.habitoplusback.enums.Status;

public class RequestWithProfileDTO {
    private Date dateRequest;
    private Status status;
    private ProfileDTO profile;
    
    public RequestWithProfileDTO(ProfileDTO profile, Date dateRequest, Status status) {
        this.dateRequest = dateRequest;
        this.status = status;
        this.profile = profile;
    }

    public RequestWithProfileDTO() {
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

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

}
