package com.upc.appcafe.dtos;

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
public class ConsumidorDTO {
    private Long id;
    private String name;
    private String apellido;
    private String email;
    private String sexo;
    private int edad;
    private User user;
}
