package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByEstadoAndFechaInicio(String estado, LocalDate fechaInicio);
    List<Evento> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Evento> findByCafeteriaId(Long cafeteriaId);
    List<Evento> findByCafeteriaNombreContainingIgnoreCase(String nombreCafeteria);
    List<Evento> findByCantidadParticipantesBetween(Long minParticipantes, Long maxParticipantes);
}
