package com.habitoplus.habitoplusback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pixela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPixela;

    // Atributos creados al mismo tiempo que la cuenta de Pixela
    private String username; // El nombre de usuario en Pixela
    private String token;  // Token del usuario en Pixela

    // Atributos creados al crear la gráfica (cuando se crea un hábito)
    private String graphId;  // ID de la gráfica en Pixela
    private String graphName; // Nombre de la gráfica
    private String unit;  // Unidad de medida de la gráfica (e.g. "commit")
    private String type;  // Tipo de la gráfica (e.g. "int")
    private String color; // Color de la gráfica (e.g. "shibafu")

    @ManyToOne
    @JoinColumn(name = "idAccount")
    private Account account;

    // Getters y setters

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
        return "Pixela [idPixela=" + idPixela + ", username=" + username + ", graphId=" + graphId + ", graphName="
                + graphName + ", token=" + token + ", unit=" + unit + ", type=" + type + ", color=" + color
                + ", account=" + account + "]";
    }
    
}
