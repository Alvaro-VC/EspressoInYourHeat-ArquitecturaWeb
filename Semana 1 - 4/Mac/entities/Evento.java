package com.upc.appcafe.entities;

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
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String titulo;
    @Column(nullable = false, length = 500)
    private String descripcion;
    private String estado;
    @ColumnDefault("now()")
    private LocalDate fechaInicio;
    @ColumnDefault("now()")
    private LocalDate fechaFin;
    @Column(nullable = false, length = 1000)
    private Long cantidadParticipantes;
    @ManyToOne
    @JoinColumn(name = "cafeteria_id", nullable = false)
    private Cafeteria cafeteria;
}
