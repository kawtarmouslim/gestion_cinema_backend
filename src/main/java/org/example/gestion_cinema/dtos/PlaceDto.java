package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.gestion_cinema.dtos.SalleDto;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto implements Serializable {
    Long id;
    int numero;
    SalleDto salle;
}
