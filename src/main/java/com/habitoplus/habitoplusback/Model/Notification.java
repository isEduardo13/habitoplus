package com.habitoplus.habitoplusback.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false) // Aquí cambia a "id_profile" para mantener consistencia.
    private Profile profile; // Usa la entidad Profile que representa la tabla de perfiles.

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "isRead", columnDefinition = "BIT") // Cambiar "read" a "isRead" o usar comillas invertidas
    private Boolean isRead; // Cambiar el nombre aquí también

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
   }

    public void setProfile(Profile profile) {
       this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        this.isRead = read;
    }
}
