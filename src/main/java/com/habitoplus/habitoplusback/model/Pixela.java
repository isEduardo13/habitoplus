package com.habitoplus.habitoplusback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pixela")
public class Pixela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPixela;
    private String username; 
    @JsonIgnore
    private String token;  
    private String graphId;  
    private String graphName; 
    private String unit;  
    private String type;  
    private String color; 

    @OneToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "idAccount")
    @JsonBackReference(value = "account-pixela")  
    private Account account;
}
