package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.CafeteriaDTO;
import com.upc.appcafe.dtos.CafeteriaFavoritaDTO;
import com.upc.appcafe.interfaces.ICafeteriaFavoritaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class CafeteriaFavoritaController {
    @Autowired
    private ICafeteriaFavoritaService iCafeteriaFavoritaService;
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PostMapping("/cafeteriafavorita")
    public ResponseEntity<CafeteriaFavoritaDTO> crear(@RequestBody CafeteriaFavoritaDTO cafeteriaFavoritaDTO) {
        cafeteriaFavoritaDTO = iCafeteriaFavoritaService.crear(cafeteriaFavoritaDTO);
        return ResponseEntity.ok(cafeteriaFavoritaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PutMapping("/cafeteriafavorita")
    public ResponseEntity<CafeteriaFavoritaDTO> actualizar(@RequestBody CafeteriaFavoritaDTO cafeteriaFavoritaDTO) {
        cafeteriaFavoritaDTO = iCafeteriaFavoritaService.actualizar(cafeteriaFavoritaDTO);
        return ResponseEntity.ok(cafeteriaFavoritaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @DeleteMapping("/cafeteriafavorita/{id}")
    public void eliminar(@PathVariable Long id) {
        iCafeteriaFavoritaService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/cafeteriafavorita/{id}")
    public ResponseEntity<CafeteriaFavoritaDTO> encontrarPorId(@PathVariable Long id) {
        CafeteriaFavoritaDTO cafeteriaFavoritaDTO = iCafeteriaFavoritaService.encontrarPorId(id);
        return ResponseEntity.ok(cafeteriaFavoritaDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/cafeteriasfavoritas")
    public ResponseEntity<List<CafeteriaFavoritaDTO>> listarTodos() {
        List<CafeteriaFavoritaDTO> lista = iCafeteriaFavoritaService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/cafeteriafavorita/consumidor/{id}")
    public ResponseEntity<List<CafeteriaFavoritaDTO>> listarFavoritasPorConsumidor(@PathVariable Long id)
    {
        List<CafeteriaFavoritaDTO> favoritas = iCafeteriaFavoritaService.findByConsumidorId(id);
        return ResponseEntity.ok(favoritas);
    }

}
