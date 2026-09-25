package org.example.domain.repository;

import org.example.domain.model.Envio;
import java.util.Optional;

public interface IEnvioRepository {
    Optional<Envio> buscarPorGuia(String idGuia);
}