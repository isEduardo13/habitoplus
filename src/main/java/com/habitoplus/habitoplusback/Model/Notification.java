package com.habitoplus.habitoplusback.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document(collection = "notification")
public class Notification {

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private Integer id;
    private String diasConsecutivos;
    private String fechaInicio;
    private String fechaFin;
    private String mensaje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer  id) {
        this.id = id;
    }               

    public String getDiasConsecutivos() {
        return diasConsecutivos;
    }

    public void setDiasConsecutivos(String diasConsecutivos) {
        this.diasConsecutivos = diasConsecutivos;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Notification [id=" + id + ", diasConsecutivos=" +
                diasConsecutivos + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin + ", mensaje=" + mensaje +
                 "]";
    }
}
