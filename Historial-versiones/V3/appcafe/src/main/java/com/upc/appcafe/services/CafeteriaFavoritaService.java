package com.upc.appcafe.services;

import com.upc.appcafe.dtos.CafeteriaFavoritaDTO;
import com.upc.appcafe.entities.Cafeteria;
import com.upc.appcafe.entities.CafeteriaFavorita;
import com.upc.appcafe.entities.Consumidor;
import com.upc.appcafe.entities.PropietarioCafeteria;
import com.upc.appcafe.interfaces.ICafeteriaFavoritaService;
import com.upc.appcafe.repositories.CafeteriaFavoritaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeteriaFavoritaService implements ICafeteriaFavoritaService {
    @Autowired
    private CafeteriaFavoritaRepository cafeteriaFavoritaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CafeteriaFavoritaDTO crear(CafeteriaFavoritaDTO cafeteriaFavoritaDTO) {
        CafeteriaFavorita cafeteriaFavorita = modelMapper.map(cafeteriaFavoritaDTO, CafeteriaFavorita.class);
        cafeteriaFavorita.setId(null);
        if (cafeteriaFavoritaRepository.existsByConsumidorIdAndCafeteriaId
                (cafeteriaFavoritaDTO.getConsumidor().getId(), cafeteriaFavoritaDTO.getCafeteria().getId())) {
            throw new IllegalArgumentException(
                    "El consumidor con id " + cafeteriaFavoritaDTO.getConsumidor().getId() + " ya tiene marcada como favorita la cafetería con id "
                            + cafeteriaFavoritaDTO.getCafeteria().getId() + "."
            );
        }
        cafeteriaFavorita = cafeteriaFavoritaRepository.save(cafeteriaFavorita);
        return modelMapper.map( cafeteriaFavorita, CafeteriaFavoritaDTO.class);
    }

    @Override
    public CafeteriaFavoritaDTO actualizar(CafeteriaFavoritaDTO cafeteriaFavoritaDTO) {
        CafeteriaFavorita existe = cafeteriaFavoritaRepository.findById(cafeteriaFavoritaDTO.getId())
                .orElseThrow(() -> new RuntimeException("Cafeteria favorita no encontrada"));
        Consumidor consumidorActual = existe.getConsumidor();
        Cafeteria cafeteriaActual = existe.getCafeteria();
        existe.setConsumidor(null);
        existe.setCafeteria(null);
        modelMapper.map(cafeteriaFavoritaDTO, existe);
        existe.setConsumidor(consumidorActual);
        existe.setCafeteria(cafeteriaActual);
        existe = cafeteriaFavoritaRepository.save(existe);
        return modelMapper.map(existe, CafeteriaFavoritaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        cafeteriaFavoritaRepository.deleteById(id);
    }

    @Override
    public CafeteriaFavoritaDTO encontrarPorId(Long id) {
        return cafeteriaFavoritaRepository.findById(id)
                .map( c -> modelMapper.map(c, CafeteriaFavoritaDTO.class))
                .orElseThrow(() -> new RuntimeException("Cafeteria favorita no encontrada"));
    }

    @Override
    public List<CafeteriaFavoritaDTO> listarTodos() {
        return cafeteriaFavoritaRepository.findAll()
                .stream().map(c -> modelMapper.map(c,CafeteriaFavoritaDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<CafeteriaFavoritaDTO> findByConsumidorId(Long consumidorId) {
        return cafeteriaFavoritaRepository.findByConsumidorId(consumidorId)
                .stream().map(c -> modelMapper.map(c,CafeteriaFavoritaDTO.class))
                .collect(Collectors.toList());
    }



}
