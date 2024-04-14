package org.example.gestion_cinema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class
SalleDto implements Serializable {
    Long id;
    String name;
    int nombrePlace;
    public SalleDto(String name) {
        this.name = name;}

}
