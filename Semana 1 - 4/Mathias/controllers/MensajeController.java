package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.MensajeDTO;
import com.upc.appcafe.interfaces.IMensajeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class MensajeController {
    @Autowired
    private IMensajeService iMensajeService;
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PostMapping("/mensaje")
    public ResponseEntity<MensajeDTO> crear(@RequestBody MensajeDTO mensajeDTO) {
        mensajeDTO = iMensajeService.crear(mensajeDTO);
        return ResponseEntity.ok(mensajeDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PutMapping("/mensaje")
    public ResponseEntity<MensajeDTO> actualizar(@RequestBody MensajeDTO mensajeDTO) {
        mensajeDTO = iMensajeService.actualizar(mensajeDTO);
        return ResponseEntity.ok(mensajeDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @DeleteMapping("/mensaje/{id}")
    public void eliminar(@PathVariable Long id) {
        iMensajeService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/mensaje/{id}")
    public ResponseEntity<MensajeDTO> encontrarPorId(@PathVariable Long id) {
        MensajeDTO mensajeDTO = iMensajeService.encontrarPorId(id);
        return ResponseEntity.ok(mensajeDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/mensajes")
    public ResponseEntity<List<MensajeDTO>> listarTodos() {
        List<MensajeDTO> lista = iMensajeService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
}
