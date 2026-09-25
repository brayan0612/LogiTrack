package org.example.domain.model;

import java.time.LocalDateTime;

public class EventoRastreo {
    private String idEvento;
    private String idGuia;
    private String ubicacion;
    private String descripcion;
    private LocalDateTime fechaHora;

    public EventoRastreo(String idEvento, String idGuia, String ubicacion, String descripcion, LocalDateTime fechaHora) {
        this.idEvento = idEvento;
        this.idGuia = idGuia;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    public String getIdEvento() { return idEvento; }
    public String getIdGuia() { return idGuia; }
    public String getUbicacion() { return ubicacion; }
    public String getDescripcion() { return descripcion; }
    public java.time.LocalDateTime getFechaHora() { return fechaHora; }

    // Genera también los Getters y Setters correspondientes
}