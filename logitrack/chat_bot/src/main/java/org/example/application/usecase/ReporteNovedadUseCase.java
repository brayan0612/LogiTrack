package org.example.application.usecase;

import org.example.domain.model.EventoRastreo;
import org.example.domain.repository.IEventoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReporteNovedadUseCase {

    private final IEventoRepository eventoRepository;

    public ReporteNovedadUseCase(IEventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public String reportarProblema(String idGuia, String ubicacion, String descripcion) {
        if (idGuia == null || descripcion == null) {
            return "⚠️ Faltan datos para registrar la novedad.";
        }

        // UUID genera un código único aleatorio para este evento específico
        String idEvento = UUID.randomUUID().toString();

        EventoRastreo nuevoEvento = new EventoRastreo(
                idEvento, idGuia.trim().toUpperCase(), ubicacion, descripcion, LocalDateTime.now()
        );

        eventoRepository.guardar(nuevoEvento);

        return "✅ Novedad registrada correctamente en el sistema para la guía: " + idGuia;
    }
}