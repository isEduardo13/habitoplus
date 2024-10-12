package com.habitoplus.habitoplusback.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfile;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Streak", referencedColumnName = "idStreak")
    private Streak streak;

    @JsonManagedReference
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;
    
    @Transient
    private Photo photo;

    private String username;

    private String name;

    private String lastName;

    private String birthDate;

    private String age;

    private String gender;

    private String preferences;

    private String description;

    private String numberPhone;

    private Boolean status;

    private String dateOfRegistration;

    private String lastConnetion;


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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String setDescription() {
        return description;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
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
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getLastConnection() {
        return lastConnetion;
    }

    public void setLastConnection(String lastConnnetion) {
        lastConnetion = lastConnnetion;
    }
    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public void  Inicializar() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = LocalDate.now().format(formatter);
        this.dateOfRegistration= formattedDate;
        this.status = true;
        this.lastConnetion = formattedDate;
        this.age = "N/A";
        this.gender = "Unknown";
        this.numberPhone = "N/A";
        this.preferences = "N/A";
        this.description = "N/A";
        this.lastName = "N/A";
        this.name = "N/A";
        this.username = "N/A";
        this.birthDate = "N/A";
        this.habits = null;
        this.streak = null;
    }
}
