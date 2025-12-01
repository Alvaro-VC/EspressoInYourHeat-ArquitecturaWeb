package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Cafeteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeteriaRepository extends JpaRepository<Cafeteria, Long> {
    //List<Cafeteria> findByPreferenciasConsumidorId(Long consumidorId);
    List<Cafeteria> findByHorarioAtencionContainingIgnoreCase(String horarioAtencion);
}
