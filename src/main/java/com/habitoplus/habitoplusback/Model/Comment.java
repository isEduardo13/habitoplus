package com.habitoplus.habitoplusback.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblComment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;

    @ManyToOne(fetch= FetchType.LAZY, optional= false)
    @JoinColumn(name= "idGroup")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Group group;

    @ManyToOne(fetch= FetchType.LAZY, optional= false)
    @JoinColumn(name= "idProfile")
    private Profile profile;

    public Group getGroup() {
        return group;
    } 

    public void setGroup(Group group) {
        this.group = group;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

}
