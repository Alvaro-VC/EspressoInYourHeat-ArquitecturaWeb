package com.upc.appcafe.interfaces;

import com.upc.appcafe.dtos.MensajeDTO;

import java.util.List;

public interface IMensajeService {
    public MensajeDTO crear(MensajeDTO mensajeDTO);
    public MensajeDTO actualizar(MensajeDTO mensajeDTO);
    public void eliminar(Long id);
    public MensajeDTO encontrarPorId(Long id);
    public List<MensajeDTO> listarTodos();
}
