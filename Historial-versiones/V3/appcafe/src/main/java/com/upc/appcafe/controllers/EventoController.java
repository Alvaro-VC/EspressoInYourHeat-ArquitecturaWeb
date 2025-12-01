package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.ConsumidorDTO;
import com.upc.appcafe.dtos.EventoDTO;
import com.upc.appcafe.entities.Evento;
import com.upc.appcafe.interfaces.IEventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class EventoController {
    @Autowired
    private IEventoService iEventoService;

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @PostMapping("/evento")
    public ResponseEntity<EventoDTO> crear(@RequestBody EventoDTO eventoDTO) {
        eventoDTO = iEventoService.crear(eventoDTO);
        return ResponseEntity.ok(eventoDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @PutMapping("/evento")
    public ResponseEntity<EventoDTO> actualizar(@RequestBody EventoDTO eventoDTO) {
        eventoDTO = iEventoService.actualizar(eventoDTO);
        return ResponseEntity.ok(eventoDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @DeleteMapping("/evento/{id}")
    public void eliminar(@PathVariable Long id) {
        iEventoService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/evento/{id}")
    public ResponseEntity<EventoDTO> encontrarPorId(@PathVariable Long id) {
        EventoDTO eventoDTO = iEventoService.encontrarPorId(id);
        return ResponseEntity.ok(eventoDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDTO>> listarTodos() {
        List<EventoDTO> lista = iEventoService.listarTodos();
        return  ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/eventos/{estado},{fechaInicio}")
    public List<EventoDTO> eventos(@PathVariable String estado, @PathVariable LocalDate fechaInicio)
    {
        return iEventoService.findByEstadoAndFechaInicio(estado, fechaInicio);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/eventos/rango/{fechaInicio},{fechaFin}")
    public List<EventoDTO> eventos(@PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin)
    {
        return iEventoService.findByFechaInicioBetween(fechaInicio, fechaFin);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/eventos/cafeteria/{id}")
    public List<EventoDTO> eventos(@PathVariable Long id)
    {
        return iEventoService.findByCafeteriaId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/eventos/cafeteria/nombre/{nombreCafeteria}")
    public List<EventoDTO> eventosPorNombreCafeteria(@PathVariable String nombreCafeteria) {
        return iEventoService.findByCafeteriaNombreContainingIgnoreCase(nombreCafeteria);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/eventos/participantes/{min}/{max}")
    public List<EventoDTO> eventosPorRangoParticipantes(@PathVariable Long min, @PathVariable Long max) {
        return iEventoService.findByCantidadParticipantesBetween(min,max);
    }
}
