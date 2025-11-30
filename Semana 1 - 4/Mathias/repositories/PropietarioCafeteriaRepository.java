package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.PropietarioCafeteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropietarioCafeteriaRepository extends JpaRepository<PropietarioCafeteria, Long> {
    Long findByUser_Id(Long userId);
    /*@Query("select new com.upc.apimathiascueva.msdtos.CantidadExamenesPorCitaDTO(el.citaMedica.paciente, count (el.citaMedica))"
            + "from ExamenLaboratorio el where el.citaMedica.fecha >= :fechaInicio " +
            "and el.citaMedica.fecha<= :fechaFin group by el.citaMedica.paciente")
    List<CantidadExamenesPorCitaDTO> listarreporte(LocalDate fechaInicio, LocalDate fechaFin);*/

    @Query("SELECT p.id FROM PropietarioCafeteria p WHERE p.user.id = :userId")
    Long findIdByUserId(Long userId);

}
