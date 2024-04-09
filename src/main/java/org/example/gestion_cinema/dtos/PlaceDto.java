package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.gestion_cinema.entites.Place;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlaceDto implements Serializable {
    Long id;
    String numero;
    String rangee;
    boolean estReservee;
}
