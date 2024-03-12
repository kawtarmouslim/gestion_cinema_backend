package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDto implements Serializable {
    Long id;
    String name;
    String adresse;
    int nombreSalles;
    VilleDto ville;
    //    Collection<SalleDto> salles;
}
