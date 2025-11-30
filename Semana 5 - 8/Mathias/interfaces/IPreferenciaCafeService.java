package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.PreferenciaCafeDTO;
import com.upc.appcafe.entities.PreferenciaCafe;

import java.util.List;

public interface IPreferenciaCafeService {
    public PreferenciaCafeDTO crear(PreferenciaCafeDTO preferenciaCafeDTO);
    public PreferenciaCafeDTO actualizar(PreferenciaCafeDTO preferenciaCafeDTO);
    public void eliminar(Long id);
    public PreferenciaCafeDTO encontrarPorId(Long id);
    public List<PreferenciaCafeDTO> listarTodos();

    public List<PreferenciaCafe> findPreferenciasByConsumidor(Long consumidorId);
}

