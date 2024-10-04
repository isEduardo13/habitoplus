package com.habitoplus.habitoplusback.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroup;
    private String name;
    private String description;
    private Date creationDate;
    private boolean privacy;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupMember> members = new HashSet<>();

    public int getIdGroup() {
        return idGroup;
    }
    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public boolean getPrivacy() {
        return privacy;
    }
    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(Set<GroupMember> members) {
        this.members = members;
    }

}
