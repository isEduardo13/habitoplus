package com.habitoplus.habitoplusback.dto;

import java.util.Date;

import com.habitoplus.habitoplusback.enums.Role;

public class GroupMemberWithProfileDTO {
    private Role role;
    private Date unionDate;
    private ProfileDTO profile;

    public GroupMemberWithProfileDTO() {
    }


    public GroupMemberWithProfileDTO(ProfileDTO profile, Role role, Date unionDate) {
        this.profile = profile;
        this.role = role;
        this.unionDate = unionDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getUnionDate() {
        return unionDate;
    }

    public void setUnionDate(Date unionDate) {
        this.unionDate = unionDate;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }


}
