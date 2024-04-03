package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsDto implements Serializable {
    Long id;
    String nom;
    String tel;
    String email;
    public ClientsDto(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

}
