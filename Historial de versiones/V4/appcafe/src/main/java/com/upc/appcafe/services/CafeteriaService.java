package com.upc.appcafe.services;

import com.upc.appcafe.dtos.CafeteriaDTO;
import com.upc.appcafe.entities.Cafeteria;
import com.upc.appcafe.entities.PropietarioCafeteria;
import com.upc.appcafe.interfaces.ICafeteriaService;
import com.upc.appcafe.repositories.CafeteriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeteriaService implements ICafeteriaService {
    @Autowired
    private CafeteriaRepository cafeteriaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CafeteriaDTO crear(CafeteriaDTO cafeteriaDTO) {
        Cafeteria cafeteria = modelMapper.map(cafeteriaDTO, Cafeteria.class);
        cafeteria.setId(null);

        cafeteria = cafeteriaRepository.save(cafeteria);
        return modelMapper.map(cafeteria, CafeteriaDTO.class);
    }

    @Override
    public CafeteriaDTO actualizar(CafeteriaDTO cafeteriaDTO) {
        Cafeteria existe = cafeteriaRepository.findById(cafeteriaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Cafeteria favorita no encontrada"));
        PropietarioCafeteria propietarioCafeteriaActual = existe.getPropietarioCafeteria();
        existe.setPropietarioCafeteria(null);
        modelMapper.map(cafeteriaDTO, existe);
        existe.setPropietarioCafeteria(propietarioCafeteriaActual);
        existe= cafeteriaRepository.save(existe);
        return modelMapper.map(existe, CafeteriaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        cafeteriaRepository.deleteById(id);
    }

    @Override
    public CafeteriaDTO encontrarPorId(Long id) {
        return cafeteriaRepository.findById(id)
                .map( c -> modelMapper.map(c, CafeteriaDTO.class))
                .orElseThrow(() -> new RuntimeException("Cafeteria no encontrada"));
    }

    @Override
    public List<CafeteriaDTO> listarTodos() {
        return cafeteriaRepository.findAll()
                .stream().map(c -> modelMapper.map(c, CafeteriaDTO.class))
                .collect(Collectors.toList());
    }
    /*
    @Override
    public List<Cafeteria> findCafeteriasByPreferencias(Long consumidorId) {
        return cafeteriaRepository.findByPreferenciasConsumidorId(consumidorId);
    }*/
    @Override
    public List<CafeteriaDTO> findByHorarioAtencionContainingIgnoreCase(String horarioAtencion) {
        return cafeteriaRepository.findByHorarioAtencionContainingIgnoreCase(horarioAtencion)
                .stream()
                .map(c -> modelMapper.map(c, CafeteriaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CafeteriaDTO> encontrarPorPropietarioId(Long propietarioId) {
        return cafeteriaRepository.findByPropietarioId(propietarioId)
                .stream()
                .map(c -> modelMapper.map(c, CafeteriaDTO.class))
                .collect(Collectors.toList());
    }
}
