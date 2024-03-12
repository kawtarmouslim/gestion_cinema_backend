package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.SeanceDto;

import java.util.List;

public interface ISeanceService {
    SeanceDto createSeance(SeanceDto seanceDto);
    SeanceDto getSeanceById(Long id);
    List<SeanceDto> getAllSeances();
    void deleteSeance(Long id);
}
