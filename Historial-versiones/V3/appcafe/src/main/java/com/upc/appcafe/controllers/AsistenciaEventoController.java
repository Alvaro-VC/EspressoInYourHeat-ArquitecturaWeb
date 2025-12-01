package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.AsistenciaEventoDTO;
import com.upc.appcafe.interfaces.IAsistenciaEventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class AsistenciaEventoController {
    @Autowired
    private IAsistenciaEventoService iAsistenciaEventoService;
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PostMapping("/asistenciaevento")
    public ResponseEntity<AsistenciaEventoDTO> crear(@RequestBody AsistenciaEventoDTO asistenciaEventoDTO) {
        asistenciaEventoDTO = iAsistenciaEventoService.crear(asistenciaEventoDTO);
        return ResponseEntity.ok(asistenciaEventoDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PutMapping("/asistenciaevento")
    public ResponseEntity<AsistenciaEventoDTO> actualizar(@RequestBody AsistenciaEventoDTO asistenciaEventoDTO) {
        asistenciaEventoDTO = iAsistenciaEventoService.actualizar(asistenciaEventoDTO);
        return ResponseEntity.ok(asistenciaEventoDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @DeleteMapping("/asistenciaevento/{id}")
    public void eliminar(@PathVariable Long id) {
        iAsistenciaEventoService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/asistenciaevento/{id}")
    public ResponseEntity<AsistenciaEventoDTO> encontrarPorId(@PathVariable Long id) {
        AsistenciaEventoDTO asistenciaEventoDTO = iAsistenciaEventoService.encontrarPorId(id);
        return ResponseEntity.ok(asistenciaEventoDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/asistenciaseventos")
    public ResponseEntity<List<AsistenciaEventoDTO>> listarTodos() {
        List<AsistenciaEventoDTO> lista = iAsistenciaEventoService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
}
