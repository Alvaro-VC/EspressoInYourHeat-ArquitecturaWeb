package com.upc.appcafe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cafeteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 500)
    private String descripcion;
    @Column(nullable = false, length = 200)
    private String direccion;
    @Column(nullable = false, length = 50)
    private String telefono;
    @Column(nullable = false, length = 200)
    private String horarioAtencion;
    @Column(nullable = false, length = 1000)
    private Long aforo;
    @ManyToOne
    @JoinColumn(name = "propietario_cafeteria_id", nullable = false)
    private PropietarioCafeteria propietarioCafeteria;
}
