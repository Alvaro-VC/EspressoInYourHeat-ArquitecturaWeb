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
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("now()")
    private LocalDate fechaMatch;

    @ManyToOne
    @JoinColumn(name = "participante1_id", nullable = false)
    private Consumidor consumidor1;

    @ManyToOne
    @JoinColumn(name = "participante2_id", nullable = false)
    private Consumidor consumidor2;
}
