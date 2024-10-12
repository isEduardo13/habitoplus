package com.habitoplus.habitoplusback.Model;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberPK implements Serializable {
    private Integer group; // Debe coincidir con el tipo de ID en Group
    private Integer profile; // Debe coincidir con el tipo de ID en Profile

    public GroupMemberPK() {}

    public GroupMemberPK(Integer group, Integer profile) {
        this.group = group;
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMemberPK)) return false;
        GroupMemberPK that = (GroupMemberPK) o;
        return Objects.equals(group, that.group) && Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, profile);
    }

    // Getters y setters
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
