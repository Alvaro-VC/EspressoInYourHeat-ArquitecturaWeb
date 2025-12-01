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
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    @ColumnDefault("now()")
    private LocalDate fechaEnviado;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Consumidor consumidor;
}
