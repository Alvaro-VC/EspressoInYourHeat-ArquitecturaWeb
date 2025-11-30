package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.ChatDTO;

import java.util.List;

public interface IChatService {
    public ChatDTO crear(ChatDTO chatDTO);
    public ChatDTO actualizar(ChatDTO chatDTO);
    public void eliminar(Long id);
    public ChatDTO encontrarPorId(Long id);
    public List<ChatDTO> listarTodos();

    public List<ChatDTO> encontrarChatsPorIdConsumidor (Long id);
}
