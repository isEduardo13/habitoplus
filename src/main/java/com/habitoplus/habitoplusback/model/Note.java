package com.habitoplus.habitoplusback.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private int idNote;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    @NotNull(message = "Profile cannot be null")
    @JsonProperty("profile")
    @JsonBackReference
    private Profile profile;
    
    @Column(name = "title", length = 50, nullable = false)
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Content cannot be empty")
    private String content;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    // Constructor vacío
    public Note() {
    }

    // Constructor completo
    public Note(int idNote, Profile profile, String title, String content, LocalDateTime date) {
        this.idNote = idNote;
        this.profile = profile;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
