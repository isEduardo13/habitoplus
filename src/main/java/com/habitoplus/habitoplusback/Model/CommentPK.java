package com.habitoplus.habitoplusback.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentPK implements Serializable{
    private Integer group; 
    private Integer profile; 
    private LocalDateTime dateTime; 

    public CommentPK() {
    }

    public CommentPK(Integer group, Integer profile, LocalDateTime dateTime) {
        this.group = group;
        this.profile = profile;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentPK)) return false;
        CommentPK that = (CommentPK) o;
        return Objects.equals(group, that.group) && Objects.equals(profile, that.profile) && (dateTime != null && dateTime.isEqual(that.dateTime));
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, profile, dateTime);
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }    
}
