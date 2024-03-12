package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.UtilisateurDto;

import java.util.List;

public interface IUtilisateurService {
    List<UtilisateurDto> getAllUtilisateut();
    UtilisateurDto getUtilisateurById(Long id);
    UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto);
    UtilisateurDto updateLivre(Long id, UtilisateurDto utilisateurDto);
    void deleteUtilisateur(Long id);
}
