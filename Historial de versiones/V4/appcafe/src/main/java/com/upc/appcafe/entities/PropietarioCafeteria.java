package com.upc.appcafe.entities;

import com.upc.appcafe.security.entities.User;
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
public class PropietarioCafeteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String apellido;
    // @Column(unique = true, nullable = false, length = 50)
    private String email;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
