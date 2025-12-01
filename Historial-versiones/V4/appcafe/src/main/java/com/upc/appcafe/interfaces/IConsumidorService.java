package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.ConsumidorDTO;
import com.upc.appcafe.entities.Consumidor;

import java.util.List;

public interface IConsumidorService {
    public ConsumidorDTO crear(ConsumidorDTO consumidorDTO);
    public ConsumidorDTO actualizar(ConsumidorDTO consumidorDTO);
    public void eliminar(Long id);
    public ConsumidorDTO encontrarPorId(Long id);
    public List<ConsumidorDTO> listarTodos();
    /*
    public List<Consumidor> findConsumidoresByEvento(Long eventoId);*/

    public Long obtenerIdPropio(Long id);
}
