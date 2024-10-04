package com.habitoplus.habitoplusback.Model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotfication;

    @JoinColumn(referencedColumnName = "idProfile",name = "id_Profile")
    @ManyToOne
    private Profile profile;

    @Column(unique = true, name = "message")
    private String message;

    @Column( name = "startDate")
    private String startDate;

    @Column( name = "endDate")
    private String endDate;

    @Column( name = "consecutiveDays")
    private int consecutiveDays;

    public Notification() {
    }

    public int getId() {
        return idNotfication;
    }
    public void setId(int idNotfication) {
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public int getConsecutiveDays() {
        return consecutiveDays;
    }
    public void setConsecutiveDays(int consecutiveDays) {
        this.consecutiveDays = consecutiveDays;
    }
    public Notification(Profile profile, String message, String startDate, String endDate, int consecutiveDays) {
        this.profile = profile;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.consecutiveDays = consecutiveDays;
    }

}
