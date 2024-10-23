package com.habitoplus.habitoplusback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @Enumerated(EnumType.STRING)
    RoleAccounts role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProfile", referencedColumnName = "idProfile")
    @JsonManagedReference(value = "account-profile")
    private Profile profile;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "account-pixela") 
    private Pixela pixela;

    @Override
    public String toString() {
        return "Account [idAccount=" + idAccount + ", email=" + email + ", password=" + password + ", status=" + status
                + ", profile=" + profile + ", pixela=" + pixela + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
    
