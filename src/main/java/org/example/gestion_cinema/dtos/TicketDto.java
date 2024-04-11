package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto implements Serializable {
    Long id;
    double prix;
    private ClientsDto client;
    PlaceDto place;
    ProjectionDto projection;
    SalleDto salle;
    FilmDto film;

    // Ajoutez ce constructeur sans arguments
//    public TicketDto() {
//    }
}

