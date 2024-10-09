package com.habitoplus.habitoplusback.Model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStreak;
    // consecutiveDays, startDate, endDate

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "idProfile")
    private Profile profile;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "streak", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Habit> habits;

    private Integer consecutiveDays;
    private Date startDate;
    private Date endDate;

    public Integer getIdStreak() {
        return idStreak;
    }

    public void setIdStreak(Integer idStreak) {
        this.idStreak = idStreak;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
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
        return "Streak [idStreak=" + idStreak + ", profile=" + profile + ", habits=" + habits + ", consecutiveDays="
                + consecutiveDays + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}
