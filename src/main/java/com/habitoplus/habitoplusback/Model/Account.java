package com.habitoplus.habitoplusback.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.data.annotation.Id;

@Entity
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "idProfile",name = "id_Profile")
    private Profile profile;

    @Column(unique = true, name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column( name = "status")
    private boolean status;

    public Account() {
    }
    public int getAccount_id() {
        return idAccount;
    }

    public void setAccount_id(int idAccount) {
        this.idAccount = idAccount;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public Account(Profile profile, String email, String password, boolean status) {
        this.profile = profile;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}

