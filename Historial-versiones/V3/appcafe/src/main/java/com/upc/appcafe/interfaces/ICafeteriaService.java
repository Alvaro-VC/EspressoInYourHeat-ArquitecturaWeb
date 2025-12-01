package com.upc.appcafe.interfaces;


import com.upc.appcafe.dtos.CafeteriaDTO;
import com.upc.appcafe.entities.Cafeteria;

import java.util.List;

public interface ICafeteriaService {
    public CafeteriaDTO crear(CafeteriaDTO cafeteriaDTO);
    public CafeteriaDTO actualizar(CafeteriaDTO cafeteriaDTO);
    public void eliminar(Long id);
    public CafeteriaDTO encontrarPorId(Long id);
    public List<CafeteriaDTO> listarTodos();

    //public List<Cafeteria> findCafeteriasByPreferencias(Long consumidorId);
    List<CafeteriaDTO> findByHorarioAtencionContainingIgnoreCase(String horarioAtencion);
}
