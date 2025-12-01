package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.PreferenciaCafeDTO;
import com.upc.appcafe.dtos.PropietarioCafeteriaDTO;

import java.util.List;

public interface IPropietarioCafeteriaService {
    public PropietarioCafeteriaDTO crear(PropietarioCafeteriaDTO propietarioCafeteriaDTO);
    public PropietarioCafeteriaDTO actualizar(PropietarioCafeteriaDTO propietarioCafeteriaDTO);
    public void eliminar(Long id);
    public PropietarioCafeteriaDTO encontrarPorId(Long id);
    public List<PropietarioCafeteriaDTO> listarTodos();
}
