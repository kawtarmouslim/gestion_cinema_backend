package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.SalleDto;

import java.util.List;

public interface ISalleService {
    SalleDto getSalleById(Long salleId);

    List<SalleDto> getAllSalles();

    SalleDto createSalle(SalleDto salleDto);

    SalleDto updateSalle(Long salleId, SalleDto salleDto);

    void deleteSalle(Long salleId);
}
