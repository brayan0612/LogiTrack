package org.example.infrastructure.persistence;

import org.example.domain.model.Envio;
import org.example.domain.repository.IEnvioRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class EnvioRepositoryAdapter implements IEnvioRepository {

    private final SpringDataEnvioRepository repository;

    public EnvioRepositoryAdapter(SpringDataEnvioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Envio> buscarPorGuia(String idGuia) {
        // Busca en la base de datos y mapea el resultado al modelo de Dominio
        return repository.findById(idGuia)
                .map(entity -> new Envio(
                        entity.getIdGuia(),
                        entity.getCliente(),
                        entity.getDestino(),
                        entity.getEstadoActual()
                ));
    }
}