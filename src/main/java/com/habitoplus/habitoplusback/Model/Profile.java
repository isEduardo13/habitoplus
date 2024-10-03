package com.habitoplus.habitoplusback.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfile;

    @OneToOne(mappedBy = "profile")
    private Account account;
    public int getIdProfile() {
        return idProfile;
    }

        // Almacena solo el ID de la foto
        private String idPhoto;

        // Campo transitorio para el objeto Photo
        @Transient
        private Photo photo;

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }
}
