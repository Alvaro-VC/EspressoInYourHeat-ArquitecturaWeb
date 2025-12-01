package com.upc.appcafe.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long cantidadParticipantes;

    private CafeteriaDTO cafeteria;
}
