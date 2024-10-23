package com.habitoplus.habitoplusback.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
//@JsonIgnoreProperties({"habits", "members", "requests"}) 
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfile;

    @OneToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "idAccount")
    @JsonBackReference(value = "account-profile")  
    private Account account;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "profile-streak")
    private Streak streak;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "profile-habits")
    private List<Habit> habits;

    @JsonManagedReference  
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;
    
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMember> members;

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


    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Streak getStreak() {
        return streak;
    }

    public void setStreak(Streak streak) {
        this.streak = streak;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
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

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    @Override
    public String toString() {
        return "Profile [idProfile=" + idProfile + ", account=" + account + ", streak=" + streak + ", habits=" + habits
                + ", members=" + members + ", requests=" + requests + ", comments=" + comments + ", photo=" + photo
                + ", username=" + username + ", name=" + name + ", lastName=" + lastName + ", birthDate=" + birthDate
                + ", age=" + age + ", gender=" + gender + ", preferences=" + preferences + ", description="
                + description + ", numberPhone=" + numberPhone + ", dateOfRegistration=" + dateOfRegistration
                + ", lastConnection=" + lastConnection + "]";
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
