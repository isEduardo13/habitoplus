package com.habitoplus.habitoplusback.Model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Comment")
public class Comment {

    @Id
    private CommentPK commentPK;

    private Integer idGroup;
    
    private Integer idProfile;

    private LocalDateTime dateTime;

    private String content;

    public Comment(String content, LocalDateTime dateTime, Integer idGroup, Integer idProfile) {
        this.content = content;
        this.dateTime = dateTime;
        this.idGroup = idGroup;
        this.idProfile = idProfile;
        this.commentPK = new CommentPK(idGroup, idProfile, dateTime);
    }

    
    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }    

    public CommentPK getCommentPK() {
        return commentPK;
    }

    public void setCommentPK(CommentPK commentPK) {
        this.commentPK = commentPK;
    }
}
