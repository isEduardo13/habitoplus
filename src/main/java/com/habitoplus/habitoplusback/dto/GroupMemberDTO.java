package com.habitoplus.habitoplusback.dto;

import com.habitoplus.habitoplusback.enums.Role;

public class GroupMemberDTO {
    private ProfileDTO profile;
    private GroupDTO group;
    private Role role;

    public GroupMemberDTO(GroupDTO group, ProfileDTO profile, Role role) {
        this.group = group;
        this.profile = profile;
        this.role = role;
    }

    public GroupMemberDTO() {
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
