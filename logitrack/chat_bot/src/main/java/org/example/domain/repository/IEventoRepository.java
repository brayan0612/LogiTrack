package org.example.domain.repository;

import org.example.domain.model.EventoRastreo;

public interface IEventoRepository {
    void guardar(EventoRastreo evento);
}