package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Cafeteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CafeteriaRepository extends JpaRepository<Cafeteria, Long> {
    //List<Cafeteria> findByPreferenciasConsumidorId(Long consumidorId);

    @Query("SELECT c FROM Cafeteria c WHERE c.propietarioCafeteria.id = :propietarioId")
    List<Cafeteria> findByPropietarioId(Long propietarioId);

    List<Cafeteria> findByHorarioAtencionContainingIgnoreCase(String horarioAtencion);
}
