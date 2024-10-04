package com.habitoplus.habitoplusback.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idApplication;
    private Date dateRequest;
    private String description;
    public int getIdApplication() {
        return idApplication;
    }
    public void setIdApplication(int idApplication) {
        this.idApplication = idApplication;
    }
    public Date getDateRequest() {
        return dateRequest;
    }
    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
