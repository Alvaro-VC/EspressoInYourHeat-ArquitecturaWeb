package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.CafeteriaFavoritaDTO;

import java.util.List;

public interface ICafeteriaFavoritaService {
    public CafeteriaFavoritaDTO crear(CafeteriaFavoritaDTO cafeteriaFavoritaDTO);
    public CafeteriaFavoritaDTO actualizar(CafeteriaFavoritaDTO cafeteriaFavoritaDTO);
    public void eliminar(Long id);
    public CafeteriaFavoritaDTO encontrarPorId(Long id);
    public List<CafeteriaFavoritaDTO> listarTodos();

    public List<CafeteriaFavoritaDTO> findByConsumidorId(Long consumidorId);

}
