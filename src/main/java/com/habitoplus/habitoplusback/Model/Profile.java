package com.habitoplus.habitoplusback.Model;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProfile;

    @OneToOne(mappedBy = "profile")
    private Account account;

    @OneToMany(mappedBy = "profile")
    private List<Notification> notifications;

    @Transient
    private Photo photo;



    public int getIdProfile() {
        return idProfile;
    }


    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }
}
