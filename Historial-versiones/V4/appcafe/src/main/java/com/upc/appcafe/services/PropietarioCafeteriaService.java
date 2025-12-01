package com.upc.appcafe.services;

import com.upc.appcafe.dtos.PropietarioCafeteriaDTO;
import com.upc.appcafe.entities.PropietarioCafeteria;
import com.upc.appcafe.interfaces.IPropietarioCafeteriaService;
import com.upc.appcafe.repositories.PropietarioCafeteriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropietarioCafeteriaService implements IPropietarioCafeteriaService {
    @Autowired
    private PropietarioCafeteriaRepository propietarioCafeteriaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PropietarioCafeteriaDTO crear(PropietarioCafeteriaDTO propietarioCafeteriaDTO) {
        PropietarioCafeteria propietarioCafeteria = modelMapper.map(propietarioCafeteriaDTO, PropietarioCafeteria.class);
        propietarioCafeteria.setId(null);
        propietarioCafeteria = propietarioCafeteriaRepository.save(propietarioCafeteria);
        return modelMapper.map(propietarioCafeteria, PropietarioCafeteriaDTO.class);
    }

    @Override
    public PropietarioCafeteriaDTO actualizar(PropietarioCafeteriaDTO propietarioCafeteriaDTO) {
        PropietarioCafeteria existe = propietarioCafeteriaRepository.findById(propietarioCafeteriaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Propietario de cafeteria no encontrado"));
        modelMapper.map(propietarioCafeteriaDTO, existe);
        existe= propietarioCafeteriaRepository.save(existe);
        return modelMapper.map(existe, PropietarioCafeteriaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        propietarioCafeteriaRepository.deleteById(id);
    }

    @Override
    public PropietarioCafeteriaDTO encontrarPorId(Long id) {
        return propietarioCafeteriaRepository.findById(id)
                .map( p -> modelMapper.map(p, PropietarioCafeteriaDTO.class))
                .orElseThrow(() -> new RuntimeException("Propietario de cafeteria no encontrado"));
    }

    @Override
    public List<PropietarioCafeteriaDTO> listarTodos() {
        return propietarioCafeteriaRepository.findAll()
                .stream().map(m -> modelMapper.map(m, PropietarioCafeteriaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long obtenerIdPropio(Long id) {
        return propietarioCafeteriaRepository.findIdByUserId(id);
    }

}
