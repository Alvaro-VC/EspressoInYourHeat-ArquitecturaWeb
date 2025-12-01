package com.upc.appcafe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PreferenciaCafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String cafe;
    @Column(nullable = false, length = 50)
    private String intensidad;
    @Column(nullable = false, length = 50)
    private String nivelAzucar;
    private boolean conLeche;
    @Column(nullable = true, length = 150)
    private String acompanamiento;
    @Column(nullable = true, length = 500)
    private String descripcionDetalle;
    @OneToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;
}
