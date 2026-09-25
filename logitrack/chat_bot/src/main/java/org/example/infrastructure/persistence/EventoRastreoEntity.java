package org.example.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_rastreo")
public class EventoRastreoEntity {

    @Id
    private String idEvento;
    private String idGuia;
    private String ubicacion;
    private String descripcion;
    private LocalDateTime fechaHora;

    public EventoRastreoEntity() {}

    public EventoRastreoEntity(String idEvento, String idGuia, String ubicacion, String descripcion, LocalDateTime fechaHora) {
        this.idEvento = idEvento;
        this.idGuia = idGuia;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    // Asegúrate de generar los Getters aquí
    public String getIdEvento() { return idEvento; }
    public String getIdGuia() { return idGuia; }
    public String getUbicacion() { return ubicacion; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFechaHora() { return fechaHora; }
}