package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.CafeteriaFavorita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeteriaFavoritaRepository extends JpaRepository<CafeteriaFavorita, Long> {
    boolean existsByConsumidorIdAndCafeteriaId(Long consumidorId, Long cafeteriaId);
    List<CafeteriaFavorita> findByConsumidorId(Long consumidorId);
}
