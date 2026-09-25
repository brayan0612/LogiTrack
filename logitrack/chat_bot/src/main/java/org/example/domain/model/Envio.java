package org.example.domain.model;

public class Envio {
    private String idGuia;
    private String cliente;
    private String destino;
    private String estadoActual;

    public Envio(String idGuia, String cliente, String destino, String estadoActual) {
        this.idGuia = idGuia;
        this.cliente = cliente;
        this.destino = destino;
        this.estadoActual = estadoActual;
    }

    // Recuerda generar los Getters y Setters con IntelliJ (Clic derecho > Generate > Getter and Setter)
    public String getIdGuia() {
        return idGuia;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDestino() {
        return destino;
    }

}