package com.upc.appcafe.services;

import com.upc.appcafe.dtos.ChatDTO;
import com.upc.appcafe.entities.Chat;
import com.upc.appcafe.entities.Consumidor;
import com.upc.appcafe.interfaces.IChatService;
import com.upc.appcafe.repositories.ChatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService implements IChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ChatDTO crear(ChatDTO chatDTO) {
        Long idConsumidor1 = chatDTO.getConsumidor1().getId();
        Long idConsumidor2 = chatDTO.getConsumidor2().getId();

        if (idConsumidor1.equals(idConsumidor2)) {
            throw new IllegalArgumentException("Un chat no puede ser creado entre el mismo consumidor.");
        }
        Chat chat = modelMapper.map(chatDTO, Chat.class);
        chat.setId(null);
        chat = chatRepository.save(chat);
        return modelMapper.map(chat, ChatDTO.class);
    }

    @Override
    public ChatDTO actualizar(ChatDTO chatDTO) {
        Chat existe = chatRepository.findById(chatDTO.getId())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        Consumidor consumidorActual1 = existe.getConsumidor1();
        Consumidor consumidorActual2 = existe.getConsumidor2();
        existe.setConsumidor1(null);
        existe.setConsumidor2(null);
        modelMapper.map(chatDTO, existe);
        existe.setConsumidor1(consumidorActual1);
        existe.setConsumidor2(consumidorActual2);

        existe= chatRepository.save(existe);
        return modelMapper.map(existe, ChatDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public ChatDTO encontrarPorId(Long id) {
        return chatRepository.findById(id)
                .map( c -> modelMapper.map(c, ChatDTO.class))
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
    }

    @Override
    public List<ChatDTO> listarTodos() {
        return chatRepository.findAll()
                .stream().map(c -> modelMapper.map(c, ChatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatDTO> encontrarChatsPorIdConsumidor(Long id) {
        return chatRepository.findAllByConsumidor(id)
                .stream().map(c -> modelMapper.map(c, ChatDTO.class))
                .collect(Collectors.toList());
    }
}
