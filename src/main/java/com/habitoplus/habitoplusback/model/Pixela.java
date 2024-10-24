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

    public Pixela() {
    }

    public Pixela(String username, String token, Account account) {
        this.username = username;
        this.token = token;
        this.account = account;
    }

    public Integer getIdPixela() {
        return idPixela;
    }

    public void setIdPixela(Integer idPixela) {
        this.idPixela = idPixela;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Pixela [idPixela=" + idPixela + ", username=" + username + ", token=" + token + ", graphId=" + graphId
                + ", graphName=" + graphName + ", unit=" + unit + ", type=" + type + ", color=" + color + ", account="
                + account + "]";
    }

    
}
