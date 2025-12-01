package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.PropietarioCafeteriaDTO;
import com.upc.appcafe.interfaces.IPropietarioCafeteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class PropietarioCafeteriaController {
    @Autowired
    private IPropietarioCafeteriaService iPropietarioCafeteriaService;

    @PostMapping("/propietariocafeteria")
    public ResponseEntity<PropietarioCafeteriaDTO> crear(@RequestBody PropietarioCafeteriaDTO propietarioCafeteriaDTO) {
        propietarioCafeteriaDTO = iPropietarioCafeteriaService.crear(propietarioCafeteriaDTO);
        return ResponseEntity.ok(propietarioCafeteriaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @PutMapping("/propietariocafeteria")
    public ResponseEntity<PropietarioCafeteriaDTO> actualizar(@RequestBody PropietarioCafeteriaDTO propietarioCafeteriaDTO) {
        propietarioCafeteriaDTO = iPropietarioCafeteriaService.actualizar(propietarioCafeteriaDTO);
        return ResponseEntity.ok(propietarioCafeteriaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @DeleteMapping("/propietariocafeteria/{id}")
    public void eliminar(@PathVariable Long id) {
        iPropietarioCafeteriaService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/propietariocafeteria/{id}")
    public ResponseEntity<PropietarioCafeteriaDTO> encontrarPorId(@PathVariable Long id) {
        PropietarioCafeteriaDTO propietarioCafeteriaDTO = iPropietarioCafeteriaService.encontrarPorId(id);
        return ResponseEntity.ok(propietarioCafeteriaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIOCAFETERIA')")
    @GetMapping("/propietarioscafeterias")
    public ResponseEntity<List<PropietarioCafeteriaDTO>> listarTodos() {
        List<PropietarioCafeteriaDTO> lista = iPropietarioCafeteriaService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
}
