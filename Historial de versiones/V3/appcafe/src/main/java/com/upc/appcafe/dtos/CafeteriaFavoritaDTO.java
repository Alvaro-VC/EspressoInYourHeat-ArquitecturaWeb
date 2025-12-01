package com.upc.appcafe.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CafeteriaFavoritaDTO {
    private Long id;
    private Boolean favorita;
    private String comentario;
    private ConsumidorDTO consumidor;
    private CafeteriaDTO cafeteria;
}
