package com.habitoplus.habitoplusback.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "profiles")
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
    
    @JsonManagedReference("profile-groups")
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMember> groups;

    @JsonManagedReference("profile-requests")
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests;

    @Transient
    private List<Comment> comments;

    @Transient
    private Photo photo;

    @NotBlank
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String username;

    @NotBlank
    @Size(min = 2, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @NotBlank
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
    private String lastName;

    @NotBlank()
    @Size(min = 1, max = 10, message = "Birth date must be between 1 and 10 characters")
    private String birthDate;

    @NotBlank
    @Size(min = 1, max = 3, message = "Age must be between 1 and 3 characters")
    private String age;
    @NotBlank
    @Size(min = 1, max = 17, message = "Gender must be between 1 and 17 characters example Male / male / MALE / M / m\r\n"+ "Female / female / FEMALE / F / f\r\n" + "Not prefer to say ")
    private String gender;

    @NotBlank
    @Size(min = 1, max = 50, message = "Preferences must be between 1 and 50 characters")
    private String preferences;

    @NotBlank
    @Size(min = 1, max = 100, message = "Description must be between 1 and 100 characters")
    private String description;
    @NotBlank
    @Size(min = 1, max = 15, message = "Number phone must be between 1 and 15 characters")
    private String numberPhone;
    @NotNull
    private Boolean status;

    @NotBlank
    @Size(min = 1, max = 10, message = "Date of registration must be between 1 and 10 characters")
    private String dateOfRegistration;

    @NotBlank
    @Size(min = 1, max = 19, message = "Last connection must be between 1 and 19 characters")
    private String lastConnection;

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
        return lastConnection;
    }

    public void setLastConnection(String lastConnnetion) {
        lastConnection = lastConnnetion;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public void Inicializar() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = LocalDate.now().format(dateFormatter);
        String formattedLastConnection = LocalDateTime.now().format(dateTimeFormatter);

        this.dateOfRegistration = formattedDate;
        this.status = true;
        this.lastConnection = formattedLastConnection;
        this.age = "xx";
        this.gender = "Prefer not to say";
        this.numberPhone = "xxx-xxx-xxxx";
        this.preferences = "preference";
        this.description = "description";
        this.lastName = "LasterName";
        this.name = "Name";
        this.username = "Username";
        this.birthDate = "xx-xx-xxxx";
        this.habits = null;
        this.streak = null;
    }
}
