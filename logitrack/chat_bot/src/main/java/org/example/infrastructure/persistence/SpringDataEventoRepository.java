package org.example.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataEventoRepository extends JpaRepository<EventoRastreoEntity, String> {
}