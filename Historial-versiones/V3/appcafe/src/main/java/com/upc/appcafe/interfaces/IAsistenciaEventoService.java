package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.AsistenciaEventoDTO;

import java.util.List;

public interface IAsistenciaEventoService {
    public AsistenciaEventoDTO crear(AsistenciaEventoDTO asistenciaEventoDTO);
    public AsistenciaEventoDTO actualizar(AsistenciaEventoDTO asistenciaEventoDTO);
    public void eliminar(Long id);
    public AsistenciaEventoDTO encontrarPorId(Long id);
    public List<AsistenciaEventoDTO> listarTodos();
}
