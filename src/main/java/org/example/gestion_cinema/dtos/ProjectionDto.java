package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gestion_cinema.entites.Seance;
import org.example.gestion_cinema.entites.Ticket;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectionDto implements Serializable {
    Long id;
    Date dateProjection;
    double prix;
    SalleDto salle;
    FilmDto film;
    Collection<Ticket> tickets;
    Seance seance;
}
