package com.habitoplus.habitoplusback.Dto;

import java.util.Date;

import com.habitoplus.habitoplusback.enums.Role;

public class GroupMemberWithProfileDto {
    private Role role;
    private Date unionDate;
    private ProfileDto profile;

    public GroupMemberWithProfileDto(ProfileDto profile, Role role, Date unionDate) {
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

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }


}
