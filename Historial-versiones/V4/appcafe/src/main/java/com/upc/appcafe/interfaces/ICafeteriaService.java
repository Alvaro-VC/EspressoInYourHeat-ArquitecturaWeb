package com.upc.appcafe.interfaces;


import com.upc.appcafe.dtos.CafeteriaDTO;

import java.util.List;

public interface ICafeteriaService {
    public CafeteriaDTO crear(CafeteriaDTO cafeteriaDTO);
    public CafeteriaDTO actualizar(CafeteriaDTO cafeteriaDTO);
    public void eliminar(Long id);
    public CafeteriaDTO encontrarPorId(Long id);
    public List<CafeteriaDTO> listarTodos();

    //public List<Cafeteria> findCafeteriasByPreferencias(Long consumidorId);
    List<CafeteriaDTO> findByHorarioAtencionContainingIgnoreCase(String horarioAtencion);


    public List<CafeteriaDTO> encontrarPorPropietarioId(Long propietarioId);
}
