package com.habitoplus.habitoplusback.model;

import java.io.Serializable;
import java.util.Objects;

public class RequestPK implements Serializable{
    private Integer group; 
    private Integer profile; 

    public RequestPK() {
    }

    public RequestPK(Integer group, Integer profile) {
        this.group = group;
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestPK)) return false;
        RequestPK that = (RequestPK) o;
        return Objects.equals(group, that.group) && Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, profile);
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
}
