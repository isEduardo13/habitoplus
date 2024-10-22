package com.habitoplus.habitoplusback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.habitoplus.habitoplusback.enums.RoleAccounts;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="account")
@JsonIgnoreProperties({"profile", "pixela"}) 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;

    @NotBlank
    @Size(min = 1, max = 50, message = "Email must be between 1 and 50 characters")
    @Column(unique = true, name = "email")
    private String email;

    @NotBlank
    @Size(min = 1, max = 15, message = "Password must be between 1 and 15 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @NotNull
    private Boolean status;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "account-profile")  // Serializa del lado de Account hacia Profile
    @Enumerated(EnumType.STRING)
    RoleAccounts role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Profile", referencedColumnName = "idProfile")
    @JsonProperty("profile")
    @JsonManagedReference
    private Profile profile;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "account-pixela")  // Serializa del lado de Account hacia Pixela
    private Pixela pixela;

    @Override
    public String toString() {
        return "Account [idAccount=" + idAccount + ", email=" + email + ", password=" + password + ", status=" + status
                + ", profile=" + profile + ", pixela=" + pixela + "]";
    }


    public Integer getIdAccount() {
        return idAccount;
    }


    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }


    public String getEmail() {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getStatus() {
        return status;
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    @Override
    public boolean isEnabled() {
        return true;
    }


    public Profile getProfile() {
        return profile;
    }


    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    public Pixela getPixela() {
        return pixela;
    }


    public void setPixela(Pixela pixela) {
        this.pixela = pixela;
    }
    
}
    
