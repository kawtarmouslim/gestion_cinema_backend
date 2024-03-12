package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.VilleDto;

import java.util.List;

public interface IVilleService {
    VilleDto createVille(VilleDto villeDto);
    VilleDto getVilleById(Long id);
    List<VilleDto> getAllVilles();
    void deleteVille(Long id);
}
