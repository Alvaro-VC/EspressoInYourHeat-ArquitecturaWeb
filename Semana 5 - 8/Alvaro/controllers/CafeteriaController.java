package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.CafeteriaDTO;
import com.upc.appcafe.entities.Cafeteria;
import com.upc.appcafe.interfaces.ICafeteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class CafeteriaController {
    @Autowired
    private ICafeteriaService iCafeteriaService;
    @PostMapping("/cafeteria")
    public ResponseEntity<CafeteriaDTO> crear(@RequestBody CafeteriaDTO cafeteriaDTO) {
        cafeteriaDTO = iCafeteriaService.crear(cafeteriaDTO);
        return ResponseEntity.ok(cafeteriaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @PutMapping("/cafeteria")
    public ResponseEntity<CafeteriaDTO> actualizar(@RequestBody CafeteriaDTO cafeteriaDTO) {
        cafeteriaDTO = iCafeteriaService.actualizar(cafeteriaDTO);
        return ResponseEntity.ok(cafeteriaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @DeleteMapping("/cafeteria/{id}")
    public void eliminar(@PathVariable Long id) {
        iCafeteriaService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/cafeteria/{id}")
    public ResponseEntity<CafeteriaDTO> encontrarPorId(@PathVariable Long id) {
        CafeteriaDTO cafeteriaDTO = iCafeteriaService.encontrarPorId(id);
        return ResponseEntity.ok(cafeteriaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/cafeterias")
    public ResponseEntity<List<CafeteriaDTO>> listarTodos() {
        List<CafeteriaDTO> lista = iCafeteriaService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/consumidores/preferencias")
    public List<Cafeteria> findCafeteriasByPreferencias(@RequestParam Long consumidorId) {
        return iCafeteriaService.findCafeteriasByPreferencias(consumidorId);
    }*/
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")    
    @GetMapping("/cafeterias/horario/{horarioAtencion}")
    public ResponseEntity<List<CafeteriaDTO>> listarPorHorarioAtencion(@PathVariable String horarioAtencion) {
        return ResponseEntity.ok(iCafeteriaService.findByHorarioAtencionContainingIgnoreCase(horarioAtencion));
    }

    @GetMapping("/cafeterias/propio/{id}")
    public ResponseEntity<List<CafeteriaDTO>> encontrarPorPropietarioId(@PathVariable Long id) {
        List<CafeteriaDTO> lista = iCafeteriaService.encontrarPorPropietarioId(id);
        return  ResponseEntity.ok(lista);
    }
}
