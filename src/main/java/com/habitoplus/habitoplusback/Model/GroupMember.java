package com.habitoplus.habitoplusback.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habitoplus.habitoplusback.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "groupmember")
@IdClass(GroupMemberPK.class)
public class GroupMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "idProfile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Profile profile;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "idGroup")
    private Group group;

    // @ValidEnum(enumClass = Role.class, message = "The role must be one of the allowed values:ADMIN, MEMBER")
    // @Pattern(regexp = "^(ADMIN|MEMBER)$", message = "The role must be one of the allowed values: ADMIN, MEMBER")
    @Enumerated(EnumType.STRING)
    private Role role;

    private Date unionDate;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public Date getUnionDate() {
        return unionDate;
    }

    public void setUnionDate(Date unionDate) {
        this.unionDate = unionDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
