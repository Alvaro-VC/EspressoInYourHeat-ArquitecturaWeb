package com.upc.appcafe.services;

import com.upc.appcafe.dtos.MensajeDTO;
import com.upc.appcafe.entities.Chat;
import com.upc.appcafe.entities.Consumidor;
import com.upc.appcafe.entities.Mensaje;
import com.upc.appcafe.interfaces.IMensajeService;
import com.upc.appcafe.repositories.ChatRepository;
import com.upc.appcafe.repositories.MensajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeService implements IMensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public MensajeDTO crear(MensajeDTO mensajeDTO) {
        Mensaje mensaje = modelMapper.map(mensajeDTO, Mensaje.class);
        mensaje.setId(null);
        //Validaciones
        Long chatId = mensajeDTO.getChat().getId();
        Long consumidorId = mensajeDTO.getConsumidor().getId();
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        if (!(chat.getConsumidor1().getId().equals(consumidorId) || chat.getConsumidor2().getId().equals(consumidorId))) {
            throw new IllegalArgumentException("Solo se aceptan IDs relacionadas."
            + " consumidor1:" +  chat.getConsumidor1().getId() + " consumidor2:" + chat.getConsumidor2().getId()
            );
        }
        //
        mensaje = mensajeRepository.save(mensaje);
        return modelMapper.map(mensaje, MensajeDTO.class);
    }
    @Override
    public MensajeDTO actualizar(MensajeDTO mensajeDTO) {
        Mensaje existe = mensajeRepository.findById(mensajeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        Consumidor consumidorActual = existe.getConsumidor();
        Chat chatActual = existe.getChat();
        existe.setConsumidor(null);
        existe.setChat(null);
        modelMapper.map(mensajeDTO, existe);
        existe.setConsumidor(consumidorActual);
        existe.setChat(chatActual);
        existe= mensajeRepository.save(existe);
        return modelMapper.map(existe, MensajeDTO.class);
    }
    @Override
    public void eliminar(Long id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    public MensajeDTO encontrarPorId(Long id) {
        return mensajeRepository.findById(id)
                .map( m -> modelMapper.map(m, MensajeDTO.class))
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
    }

    @Override
    public List<MensajeDTO> listarTodos() {
        return mensajeRepository.findAll()
                .stream().map(m -> modelMapper.map(m, MensajeDTO.class))
                .collect(Collectors.toList());
    }
}
