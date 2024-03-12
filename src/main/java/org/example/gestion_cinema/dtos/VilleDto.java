package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.gestion_cinema.entites.Cinema;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VilleDto implements Serializable {
    Long id;
    String name;
   // Collection<Cinema> cinemas;
}
