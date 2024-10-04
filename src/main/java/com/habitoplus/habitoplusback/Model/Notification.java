package com.habitoplus.habitoplusback.Model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotfication;

    @JoinColumn(referencedColumnName = "idProfile",name = "id_Profile")
    @ManyToOne
    private Profile profile;

    @Column(unique = true, name = "mensaje")
    private String mensaje;

    @Column( name = "fechaInicio")
    private String fechaInicio;

    @Column( name = "fechaFin")
    private String fechaFin;

    @Column( name = "diasConsecutivos")
    private int diasConsecutivos;

    public Notification() {
    }

    public int getId() {
        return idNotfication;
    }
    public void setId(int idNotfication) {
        this.idNotfication = idNotfication;
    }
    
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getDiasConsecutivos() {
        return diasConsecutivos;
    }
    public void setDiasConsecutivos(int diasConsecutivos) {
        this.diasConsecutivos = diasConsecutivos;
    }
    public Notification(Profile profile, String mensaje, String fechaInicio, String fechaFin, int diasConsecutivos) {
        this.profile = profile;
        this.mensaje = mensaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasConsecutivos = diasConsecutivos;
    }

}
