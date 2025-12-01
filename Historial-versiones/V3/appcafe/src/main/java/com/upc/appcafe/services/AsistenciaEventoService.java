package com.upc.appcafe.services;

import com.upc.appcafe.dtos.AsistenciaEventoDTO;
import com.upc.appcafe.entities.AsistenciaEvento;
import com.upc.appcafe.entities.Consumidor;
import com.upc.appcafe.entities.Evento;
import com.upc.appcafe.interfaces.IAsistenciaEventoService;
import com.upc.appcafe.repositories.AsistenciaEventoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenciaEventoService implements IAsistenciaEventoService {
    @Autowired
    private AsistenciaEventoRepository asistenciaEventoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AsistenciaEventoDTO crear(AsistenciaEventoDTO asistenciaEventoDTO) {
        AsistenciaEvento asistenciaEvento = modelMapper.map(asistenciaEventoDTO, AsistenciaEvento.class);
        asistenciaEvento.setId(null);
        asistenciaEvento = asistenciaEventoRepository.save(asistenciaEvento);
        return modelMapper.map(asistenciaEvento, AsistenciaEventoDTO.class);
    }

    @Override
    public AsistenciaEventoDTO actualizar(AsistenciaEventoDTO asistenciaEventoDTO) {
        AsistenciaEvento existe = asistenciaEventoRepository.findById(asistenciaEventoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Asistencia de evento no encontrada"));
        Consumidor consumidorActual = existe.getConsumidor();
        Evento eventoActual = existe.getEvento();
        existe.setConsumidor(null);
        existe.setEvento(null);
        modelMapper.map(asistenciaEventoDTO, existe);
        existe.setConsumidor(consumidorActual);
        existe.setEvento(eventoActual);

        existe= asistenciaEventoRepository.save(existe);
        return modelMapper.map(existe, AsistenciaEventoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        asistenciaEventoRepository.deleteById(id);
    }

    @Override
    public AsistenciaEventoDTO encontrarPorId(Long id) {
        return asistenciaEventoRepository.findById(id)
                .map( a -> modelMapper.map(a, AsistenciaEventoDTO.class))
                .orElseThrow(() -> new RuntimeException("Asistencia de evento no encontrada"));
    }

    @Override
    public List<AsistenciaEventoDTO> listarTodos() {
        return asistenciaEventoRepository.findAll()
                .stream().map(a -> modelMapper.map(a,AsistenciaEventoDTO.class))
                .collect(Collectors.toList());
    }
}
