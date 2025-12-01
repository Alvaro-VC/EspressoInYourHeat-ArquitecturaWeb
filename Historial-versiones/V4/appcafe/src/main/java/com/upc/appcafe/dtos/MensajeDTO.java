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
public class MensajeDTO {
    private Long id;
    private String text;
    private LocalDate fechaEnviado;

    private ChatDTO chat;
    private ConsumidorDTO consumidor;
}
