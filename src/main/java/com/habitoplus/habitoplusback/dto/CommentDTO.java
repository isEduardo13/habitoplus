package com.habitoplus.habitoplusback.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentDTO implements Serializable {
    private final Integer idGroup;
    private final Integer idProfile;
    private final String content;
    private final LocalDateTime dateTime;

    private CommentDTO(Builder builder) {
        this.content = builder.content;
        this.dateTime = builder.dateTime;
        this.idGroup = builder.idGroup;
        this.idProfile = builder.idProfile;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static class Builder {
        private Integer idGroup;
        private Integer idProfile;
        private String content;
        private LocalDateTime dateTime;

        public Builder idGroup(Integer idGroup) {
            this.idGroup = idGroup;
            return this;
        }

        public Builder idProfile(Integer idProfile) {
            this.idProfile = idProfile;
            return this;

        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public CommentDTO build(){
            return new CommentDTO(this);
        }

    }

}
