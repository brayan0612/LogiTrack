package org.example.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEnvioRepository extends JpaRepository<EnvioEntity, String> {
    // JpaRepository ya incluye el método findById(String id) por defecto
}