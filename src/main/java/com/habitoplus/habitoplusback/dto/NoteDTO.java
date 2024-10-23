package com.habitoplus.habitoplusback.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NoteDTO {

    private int idNote;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @NotNull(message = "IdProfile cannot be null")
    private int idProfile;

    public NoteDTO() {}

    public NoteDTO(int idNote, String title, String content, LocalDateTime date, int idProfile) {
        this.idNote = idNote;
        this.title = title;
        this.content = content;
        this.date = date;
        this.idProfile = idProfile;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
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

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }
}
