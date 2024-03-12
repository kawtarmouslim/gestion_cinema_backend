package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto implements Serializable {
    Long id;
    String nom;
    String tel;
}
