package com.upc.appcafe.repositories;

import com.upc.appcafe.entities.PropietarioCafeteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropietarioCafeteriaRepository extends JpaRepository<PropietarioCafeteria, Long> {
    /*@Query("select new com.upc.apimathiascueva.msdtos.CantidadExamenesPorCitaDTO(el.citaMedica.paciente, count (el.citaMedica))"
            + "from ExamenLaboratorio el where el.citaMedica.fecha >= :fechaInicio " +
            "and el.citaMedica.fecha<= :fechaFin group by el.citaMedica.paciente")
    List<CantidadExamenesPorCitaDTO> listarreporte(LocalDate fechaInicio, LocalDate fechaFin);*/


}
