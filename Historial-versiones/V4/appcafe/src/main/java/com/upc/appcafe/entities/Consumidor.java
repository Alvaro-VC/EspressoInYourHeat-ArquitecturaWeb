package com.upc.appcafe.entities;

import com.upc.appcafe.security.entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Consumidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String apellido;
    // @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String sexo;
    @Column(nullable = false, length = 150)
    private int edad;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
