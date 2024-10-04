package com.habitoplus.habitoplusback.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStreak;
    //perfil_Id, consecutiveDays, startDate, endDate
    private int idProfile;
    private int consecutiveDays;
    private Date startDate;
    private Date endDate;

    public Integer getIdStreak() {
        return idStreak;
    }


    public void setIdStreak(Integer idStreak) {
        this.idStreak = idStreak;
    }

    public Integer getIdProfile() {
        return idProfile;
    }



    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }



    public Integer getConsecutiveDays() {
        return consecutiveDays;
    }



    public void setConsecutiveDays(Integer consecutiveDays) {
        this.consecutiveDays = consecutiveDays;
    }



    public Date getStartDate() {
        return startDate;
    }



    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Streak [idStreak=" + idStreak + ", idProfile=" + idProfile + ", consecutiveDays=" + consecutiveDays
                + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}
