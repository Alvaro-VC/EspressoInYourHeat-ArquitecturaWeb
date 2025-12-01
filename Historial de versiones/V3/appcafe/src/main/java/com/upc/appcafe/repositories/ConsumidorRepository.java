package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Long> {
    /*List<Consumidor> findByEventosId(Long eventoId);*/
}
