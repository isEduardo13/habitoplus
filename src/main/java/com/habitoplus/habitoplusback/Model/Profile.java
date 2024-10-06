package com.habitoplus.habitoplusback.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Transient;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfile;
    
    @Transient
    private Photo photo;

    private String username;

    private String name;

    private String lastName;

    private String birthDate;

    private String age;

    private String gener;

    private String Preferences;

    private String desciption;

    private String numberPhone;

    private Boolean status;

    private String DateOfRegistration;

    private String LastConnnetion;


    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    public String getPreferences() {
        return Preferences;
    }

    public void setPreferences(String preferences) {
        Preferences = preferences;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDateOfRegistration() {
        return DateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        DateOfRegistration = dateOfRegistration;
    }

    public String getLastConnnetion() {
        return LastConnnetion;
    }

    public void setLastConnnetion(String lastConnnetion) {
        LastConnnetion = lastConnnetion;
    }
    public int getIdProfile() {
        return idProfile;
    }


    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }
}
