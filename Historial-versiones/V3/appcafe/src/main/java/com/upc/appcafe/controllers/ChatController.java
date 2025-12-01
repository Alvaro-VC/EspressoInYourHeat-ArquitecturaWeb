package com.upc.appcafe.controllers;

import com.upc.appcafe.dtos.CafeteriaFavoritaDTO;
import com.upc.appcafe.dtos.ChatDTO;
import com.upc.appcafe.interfaces.IChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    private IChatService iChatService;
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PostMapping("/chat")
    public ResponseEntity<ChatDTO> crear(@RequestBody ChatDTO chatDTO) {
        chatDTO = iChatService.crear(chatDTO);
        return ResponseEntity.ok(chatDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @PutMapping("/chat")
    public ResponseEntity<ChatDTO> actualizar(@RequestBody ChatDTO chatDTO) {
        chatDTO = iChatService.actualizar(chatDTO);
        return ResponseEntity.ok(chatDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @DeleteMapping("/chat/{id}")
    public void eliminar(@PathVariable Long id) {
        iChatService.eliminar(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/chat/{id}")
    public ResponseEntity<ChatDTO> encontrarPorId(@PathVariable Long id) {
        ChatDTO chatDTO = iChatService.encontrarPorId(id);
        return ResponseEntity.ok(chatDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSUMIDOR')")
    @GetMapping("/chats")
    public ResponseEntity<List<ChatDTO>> listarTodos() {
        List<ChatDTO> lista = iChatService.listarTodos();
        return  ResponseEntity.ok(lista);
    }
}
