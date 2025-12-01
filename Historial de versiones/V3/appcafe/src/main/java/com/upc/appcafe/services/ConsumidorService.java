package com.upc.appcafe.services;

import com.upc.appcafe.dtos.ConsumidorDTO;
import com.upc.appcafe.entities.Consumidor;
import com.upc.appcafe.interfaces.IConsumidorService;
import com.upc.appcafe.repositories.ConsumidorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumidorService implements IConsumidorService {
    @Autowired
    private ConsumidorRepository consumidorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ConsumidorDTO crear(ConsumidorDTO consumidorDTO) {
        Consumidor consumidor = modelMapper.map(consumidorDTO, Consumidor.class);
        consumidor.setId(null);
        consumidor = consumidorRepository.save(consumidor);
        return modelMapper.map(consumidor, ConsumidorDTO.class);
    }

    @Override
    public ConsumidorDTO actualizar(ConsumidorDTO consumidorDTO) {
        Consumidor existe = consumidorRepository.findById(consumidorDTO.getId())
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado"));
        modelMapper.map(consumidorDTO, existe);
        existe= consumidorRepository.save(existe);
        return modelMapper.map(existe, ConsumidorDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        consumidorRepository.deleteById(id);
    }

    @Override
    public ConsumidorDTO encontrarPorId(Long id) {
        return consumidorRepository.findById(id)
                .map( c -> modelMapper.map(c, ConsumidorDTO.class))
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado"));
    }

    @Override
    public List<ConsumidorDTO> listarTodos() {
        return consumidorRepository.findAll()
                .stream().map(c -> modelMapper.map(c, ConsumidorDTO.class))
                .collect(Collectors.toList());
    }
    /*
    @Override
    public List<Consumidor> findConsumidoresByEvento(Long eventoId) {
        return consumidorRepository.findByEventosId(eventoId);
    }*/
}
