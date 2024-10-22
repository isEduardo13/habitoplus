package com.habitoplus.habitoplusback.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.habitoplus.habitoplusback.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "request")
@IdClass(RequestPK.class)
public class Request {
    @Id
    @JsonBackReference("profile-requests")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    @Id
    @JsonBackReference("group-requests")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    @Column(nullable = false, name="date_request")
    private Date dateRequest;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
