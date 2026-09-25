package org.example.application.usecase;

import org.example.domain.model.Envio;
import org.example.domain.repository.IEnvioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RastreoUseCase {

    private final IEnvioRepository envioRepository;

    // Inyección de dependencias: Spring Boot inyecta automáticamente el EnvioRepositoryAdapter aquí
    public RastreoUseCase(IEnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public String consultarEstado(String idGuia) {
        // 1. Validación básica de la entrada (el estímulo del usuario)
        if (idGuia == null || idGuia.trim().isEmpty()) {
            return "⚠️ Por favor, ingresa un número de guía válido.";
        }

        // 2. Consulta al dominio (limpiando espacios y pasando a mayúsculas por seguridad)
        Optional<Envio> envioOpt = envioRepository.buscarPorGuia(idGuia.trim().toUpperCase());

        // 3. Retornar el texto formateado que el bot enviará al usuario
        if (envioOpt.isPresent()) {
            Envio envio = envioOpt.get();
            return String.format("📦 *Guía: %s*\n👤 Cliente: %s\n📍 Destino: %s\n🚚 Estado Actual: *%s*",
                    envio.getIdGuia(), envio.getCliente(), envio.getDestino(), envio.getEstadoActual());
        } else {
            return "❌ No logré encontrar la guía '" + idGuia + "' en nuestros registros. Verifica el número e intenta nuevamente.";
        }
    }
}