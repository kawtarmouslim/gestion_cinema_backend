package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gestion_cinema.entites.Projection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto implements Serializable {
    Long id;
    String titre;
    String realisateur;
    Date dateFilm;
    String genre;
    String cheminImage;


}
