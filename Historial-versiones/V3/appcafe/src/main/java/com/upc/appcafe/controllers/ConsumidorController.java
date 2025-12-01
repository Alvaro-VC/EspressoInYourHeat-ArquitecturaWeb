package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.ConsumidorDTO;
import com.upc.appcafe.interfaces.IConsumidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class ConsumidorController {
    @Autowired
    private IConsumidorService iConsumidorService;

    @PostMapping("/consumidor")
    public ResponseEntity<ConsumidorDTO> crear(@RequestBody ConsumidorDTO consumidorDTO) {
        consumidorDTO = iConsumidorService.crear(consumidorDTO);
        return ResponseEntity.ok(consumidorDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PutMapping("/consumidor")
    public ResponseEntity<ConsumidorDTO> actualizar(@RequestBody ConsumidorDTO consumidorDTO) {
        consumidorDTO = iConsumidorService.actualizar(consumidorDTO);
        return ResponseEntity.ok(consumidorDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @DeleteMapping("/consumidor/{id}")
    public void eliminar(@PathVariable Long id) {
        iConsumidorService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/consumidor/{id}")
    public ResponseEntity<ConsumidorDTO> encontrarPorId(@PathVariable Long id) {
        ConsumidorDTO consumidorDTO = iConsumidorService.encontrarPorId(id);
        return ResponseEntity.ok(consumidorDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/consumidores")
    public ResponseEntity<List<ConsumidorDTO>> listarTodos() {
        List<ConsumidorDTO> lista = iConsumidorService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/consumidores/asistencia/evento")
    public List<Consumidor> getConsumidoresByEvento(@RequestParam Long eventoId) {
        return iConsumidorService.findConsumidoresByEvento(eventoId);
    }*/
}
