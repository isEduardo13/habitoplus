package com.habitoplus.habitoplusback.dto;

import com.habitoplus.habitoplusback.enums.Status;

public class RequestDTO {
    private GroupDTO group;
    private ProfileDTO profile;
    private Status status;

    public RequestDTO() {
    }

    public RequestDTO(GroupDTO group, ProfileDTO profile, Status status) {
        this.group = group;
        this.profile = profile;
        this.status = status;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
