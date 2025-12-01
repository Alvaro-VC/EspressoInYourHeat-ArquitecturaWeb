package com.upc.appcafe.services;

import com.upc.appcafe.dtos.EventoDTO;
import com.upc.appcafe.entities.Cafeteria;
import com.upc.appcafe.entities.Evento;
import com.upc.appcafe.interfaces.IEventoService;
import com.upc.appcafe.repositories.EventoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService implements IEventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EventoDTO crear(EventoDTO eventoDTO) {
        Evento evento = modelMapper.map(eventoDTO, Evento.class);
        evento.setId(null);
        evento = eventoRepository.save(evento);
        return modelMapper.map(evento, EventoDTO.class);
    }

    @Override
    public EventoDTO actualizar(EventoDTO eventoDTO) {
        Evento existe = eventoRepository.findById(eventoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        Cafeteria cafeteriaActual = existe.getCafeteria();
        existe.setCafeteria(null);
        modelMapper.map(eventoDTO, existe);
        existe.setCafeteria(cafeteriaActual);
        existe= eventoRepository.save(existe);
        return modelMapper.map(existe, EventoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public EventoDTO encontrarPorId(Long id) {
        return eventoRepository.findById(id)
                .map( c -> modelMapper.map(c, EventoDTO.class))
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    @Override
    public List<EventoDTO> listarTodos() {
        return eventoRepository.findAll()
                .stream().map(e -> modelMapper.map(e, EventoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventoDTO> findByEstadoAndFechaInicio(String estado, LocalDate fechaInicio) {
        return eventoRepository.findByEstadoAndFechaInicio(estado, fechaInicio)
                .stream().map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventoDTO> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return eventoRepository.findByFechaInicioBetween(fechaInicio, fechaFin)
                .stream().map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventoDTO> findByCafeteriaId(Long cafeteriaId) {
        return eventoRepository.findByCafeteriaId(cafeteriaId)
                .stream().map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<EventoDTO> findByCafeteriaNombreContainingIgnoreCase(String nombreCafeteria) {
        return eventoRepository.findByCafeteriaNombreContainingIgnoreCase(nombreCafeteria)
                .stream().map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventoDTO> findByCantidadParticipantesBetween(Long minParticipantes, Long maxParticipantes) {
        return eventoRepository.findByCantidadParticipantesBetween(minParticipantes, maxParticipantes)
                .stream().map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
}
