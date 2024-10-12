package com.habitoplus.habitoplusback.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habitoplus.habitoplusback.enums.Status;

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
@Table(name="request")
@IdClass(RequestPK.class)
public class Request {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idProfile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Profile profile;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idGroup")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Group group;
    
    private Date dateRequest;

    // @NotNull(message = "El status no puede ser nulo")
    // @ValidEnum(enumClass = Status.class, message = "El status debe ser uno de los valores permitidos: ACTIVE, INACTIVE, DELETED")
    @Enumerated(EnumType.STRING)
    private Status status;
    
    public Date getDateRequest() {
        return dateRequest;
    }
    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    // public int getIdRequest() {
    //     return idRequest;
    // }

    // public void setIdRequest(int idRequest) {
    //     this.idRequest = idRequest;
    // }

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
