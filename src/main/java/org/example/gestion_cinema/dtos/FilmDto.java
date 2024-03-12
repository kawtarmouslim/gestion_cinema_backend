package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gestion_cinema.entites.Projection;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto implements Serializable {
    Long id;
    String titre;
    String realisateur;
    int duree;
    String genre;
    String cheminImage;
    Collection<Projection> projections;

}
