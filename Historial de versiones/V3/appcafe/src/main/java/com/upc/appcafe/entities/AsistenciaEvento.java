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
public class AsistenciaEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ColumnDefault("now()")
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "consumidor_id", nullable = false)
    private  Consumidor consumidor;
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;
}
