package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.EventoDTO;
import com.upc.appcafe.entities.Evento;

import java.time.LocalDate;
import java.util.List;

public interface IEventoService {
    public EventoDTO crear(EventoDTO eventoDTO);
    public EventoDTO actualizar(EventoDTO eventoDTO);
    public void eliminar(Long id);
    public EventoDTO encontrarPorId(Long id);
    public List<EventoDTO> listarTodos();

    //Querys
    public List<EventoDTO> findByEstadoAndFechaInicio(String estado, LocalDate fechaInicio);
    public List<EventoDTO> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);
    public List<EventoDTO> findByCafeteriaId(Long cafeteriaId);
    public List<EventoDTO> findByCafeteriaNombreContainingIgnoreCase(String nombreCafeteria);
    public List<EventoDTO> findByCantidadParticipantesBetween(Long minParticipantes, Long maxParticipantes);
}
