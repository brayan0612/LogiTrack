package org.example.infrastructure.persistence;

import org.example.domain.model.EventoRastreo;
import org.example.domain.repository.IEventoRepository;
import org.springframework.stereotype.Component;

@Component
public class EventoRepositoryAdapter implements IEventoRepository {

    private final SpringDataEventoRepository repository;

    public EventoRepositoryAdapter(SpringDataEventoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(EventoRastreo evento) {
        EventoRastreoEntity entity = new EventoRastreoEntity(
                evento.getIdEvento(),
                evento.getIdGuia(),
                evento.getUbicacion(),
                evento.getDescripcion(),
                evento.getFechaHora()
        );
        repository.save(entity);
    }
}