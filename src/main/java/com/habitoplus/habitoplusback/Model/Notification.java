package com.habitoplus.habitoplusback.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNotification")
    @JsonProperty("idNotification")
    private Integer idNotfication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "id_Profile")
    @JsonBackReference
    private Profile profile;

    @NotBlank(message = "Message is mandatory")
    @Size(min = 5, max = 100, message = "Message must be between 5 and 100 characters")
    @Column(name = "message")
    @JsonProperty("message")
    private String message;

    @Column(name = "type")
    @JsonProperty("type")
    private String  type;

    @Column(name = "date")
    @JsonProperty("date")
    private String  date;

    @Column(name = "read")
    @JsonProperty("read")
    private Boolean read;

    public Integer getId() {
        return idNotfication;
    }
    public void setId(Integer idNotfication) {
        this.idNotfication = idNotfication;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Boolean getRead() {
        return read;
    }
    public void setRead(Boolean read) {
        this.read = read;
    }

}
