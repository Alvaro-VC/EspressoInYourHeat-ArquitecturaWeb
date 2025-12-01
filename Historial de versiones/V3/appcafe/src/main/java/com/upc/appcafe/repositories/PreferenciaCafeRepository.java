package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.PreferenciaCafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenciaCafeRepository extends JpaRepository<PreferenciaCafe, Long> {
    public boolean existsByConsumidorId(Long consumidorId);

    List<PreferenciaCafe> findByConsumidorId(Long consumidorId);
}
