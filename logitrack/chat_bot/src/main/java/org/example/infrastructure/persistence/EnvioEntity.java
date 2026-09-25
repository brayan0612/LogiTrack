package org.example.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "envios")
public class EnvioEntity {

    @Id
    private String idGuia;
    private String cliente;
    private String destino;
    private String estadoActual;

    // JPA requiere un constructor vacío obligatorio
    public EnvioEntity() {}

    public EnvioEntity(String idGuia, String cliente, String destino, String estadoActual) {
        this.idGuia = idGuia;
        this.cliente = cliente;
        this.destino = destino;
        this.estadoActual = estadoActual;
    }

    // Genera aquí los Getters y Setters
    public String getIdGuia() { return idGuia; }
    public String getCliente() { return cliente; }
    public String getDestino() { return destino; }
    public String getEstadoActual() { return estadoActual; }
}