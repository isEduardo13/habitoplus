package com.habitoplus.habitoplusback.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroupMember;

    @ManyToOne
    @JoinColumn(name = "idProfile")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "idGroup")
    private Group group;

    private String role;

    private Date unionDate;

    public int getIdGroupMember() {
        return idGroupMember;
    }

    public void setIdGroupMember(int idGroupMember) {
        this.idGroupMember = idGroupMember;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getUnionDate() {
        return unionDate;
    }

    public void setUnionDate(Date unionDate) {
        this.unionDate = unionDate;
    }


}
