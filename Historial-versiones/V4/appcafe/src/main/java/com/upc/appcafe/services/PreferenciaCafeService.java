package com.upc.appcafe.services;

import com.upc.appcafe.dtos.PreferenciaCafeDTO;
import com.upc.appcafe.entities.Consumidor;
import com.upc.appcafe.entities.PreferenciaCafe;
import com.upc.appcafe.interfaces.IPreferenciaCafeService;
import com.upc.appcafe.repositories.ConsumidorRepository;
import com.upc.appcafe.repositories.PreferenciaCafeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferenciaCafeService implements IPreferenciaCafeService {
    @Autowired
    private PreferenciaCafeRepository preferenciaCafeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConsumidorRepository consumidorRepository;

    @Override
    public PreferenciaCafeDTO crear(PreferenciaCafeDTO preferenciaCafeDTO) {
        PreferenciaCafe preferenciaCafe = modelMapper.map(preferenciaCafeDTO, PreferenciaCafe.class);
        preferenciaCafe.setId(null);
        if (preferenciaCafeRepository.existsByConsumidorId(preferenciaCafe.getConsumidor().getId())) {
            throw new IllegalArgumentException("El consumidor con id " + preferenciaCafe.getConsumidor().getId() + " ya tiene una preferencia registrada.");
        }
        preferenciaCafe = preferenciaCafeRepository.save(preferenciaCafe);
        return modelMapper.map(preferenciaCafe, PreferenciaCafeDTO.class);
    }

    @Override
    public PreferenciaCafeDTO actualizar(PreferenciaCafeDTO preferenciaCafeDTO) {
        PreferenciaCafe existe = preferenciaCafeRepository.findById(preferenciaCafeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Preferencia de cafe no encontrada"));
        Consumidor consumidorActual = existe.getConsumidor();
        existe.setConsumidor(null);
        modelMapper.map(preferenciaCafeDTO, existe);
        existe.setConsumidor(consumidorActual);
        existe= preferenciaCafeRepository.save(existe);

        return modelMapper.map(existe, PreferenciaCafeDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        preferenciaCafeRepository.deleteById(id);
    }

    @Override
    public PreferenciaCafeDTO encontrarPorId(Long id) {
        return preferenciaCafeRepository.findById(id)
                .map( p -> modelMapper.map(p, PreferenciaCafeDTO.class))
                .orElseThrow(() -> new RuntimeException("Preferencia de cafe no encontrado"));
    }

    @Override
    public List<PreferenciaCafeDTO> listarTodos() {
        return preferenciaCafeRepository.findAll()
                .stream().map(m -> modelMapper.map(m, PreferenciaCafeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PreferenciaCafe> findPreferenciasByConsumidor(Long consumidorId) {
        return preferenciaCafeRepository.findByConsumidorId(consumidorId);
    }
}
