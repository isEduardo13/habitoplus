package com.habitoplus.habitoplusback.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;
    @NotBlank
    @Size(min =  1 ,max = 50, message = "Email must be between 1 and 50 characters")
    @Column(unique = true, name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column( name = "status")
    private Boolean status;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Profile", referencedColumnName = "idProfile")
    @JsonProperty("profile")
    @JsonManagedReference
    private Profile profile;

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
}

