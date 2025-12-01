package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.PreferenciaCafeDTO;
import com.upc.appcafe.entities.PreferenciaCafe;
import com.upc.appcafe.interfaces.IPreferenciaCafeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class PreferenciaCafeController {
    @Autowired
    private IPreferenciaCafeService iPreferenciaCafeService;
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PostMapping("/preferenciacafe")
    public ResponseEntity<PreferenciaCafeDTO> crear(@RequestBody PreferenciaCafeDTO preferenciaCafeDTO) {
        preferenciaCafeDTO = iPreferenciaCafeService.crear(preferenciaCafeDTO);
        return ResponseEntity.ok(preferenciaCafeDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PutMapping("/preferenciacafe")
    public ResponseEntity<PreferenciaCafeDTO> actualizar(@RequestBody PreferenciaCafeDTO preferenciaCafeDTO) {
        preferenciaCafeDTO = iPreferenciaCafeService.actualizar(preferenciaCafeDTO);
        return ResponseEntity.ok(preferenciaCafeDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @DeleteMapping("/preferenciacafe/{id}")
    public void eliminar(@PathVariable Long id) {
        iPreferenciaCafeService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/preferenciacafe/{id}")
    public ResponseEntity<PreferenciaCafeDTO> encontrarPorId(@PathVariable Long id) {
        PreferenciaCafeDTO preferenciaCafeDTO = iPreferenciaCafeService.encontrarPorId(id);
        return ResponseEntity.ok(preferenciaCafeDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/preferenciascafes")
    public ResponseEntity<List<PreferenciaCafeDTO>> listarTodos() {
        List<PreferenciaCafeDTO> lista = iPreferenciaCafeService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/preferenciascafe/consumidor")
    public List<PreferenciaCafe> getPreferenciasByConsumidor(@RequestParam Long consumidorId) {
        return iPreferenciaCafeService.findPreferenciasByConsumidor(consumidorId);
    }

}
