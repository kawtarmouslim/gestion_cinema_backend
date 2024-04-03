package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.example.gestion_cinema.entites.Seance;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceDto implements Serializable {
    private Long id;
    private LocalTime heureDebut;
}
