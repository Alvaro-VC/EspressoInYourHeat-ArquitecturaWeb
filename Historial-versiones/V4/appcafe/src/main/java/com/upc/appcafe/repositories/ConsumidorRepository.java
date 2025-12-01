package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Long> {
    /*List<Consumidor> findByEventosId(Long eventoId);*/
    @Query("SELECT p.id FROM Consumidor p WHERE p.user.id = :userId")
    Long findIdByUserId(Long userId);
}
