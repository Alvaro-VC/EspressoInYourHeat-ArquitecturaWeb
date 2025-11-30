package com.upc.appcafe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreferenciaCafeDTO {
    private Long id;
    private String cafe;
    private String intensidad;
    private String nivelAzucar;
    private boolean conLeche;
    private String acompanamiento;
    private String descripcionDetalle;
    private ConsumidorDTO consumidor;
}
