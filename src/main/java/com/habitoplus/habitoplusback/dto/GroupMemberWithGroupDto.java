package com.habitoplus.habitoplusback.dto;

import java.util.Date;

import com.habitoplus.habitoplusback.enums.Role;

public class GroupMemberWithGroupDto {
    private Role role;
    private Date unionDate;
    private GroupDto group;

    public GroupMemberWithGroupDto(GroupDto group, Role role, Date unionDate) {
        this.group = group;
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

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }


}
