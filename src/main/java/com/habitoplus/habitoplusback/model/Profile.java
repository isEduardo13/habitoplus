package com.habitoplus.habitoplusback.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfile;

    @OneToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "idAccount")
    @JsonBackReference(value = "account-profile")  // Evita serialización recíproca
    private Account account;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "profile-streak")
    private Streak streak;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "profile-habits")
    private List<Habit> habits;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMember> groups;

    @JsonManagedReference
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @JsonManagedReference
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;
    
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

  

}
