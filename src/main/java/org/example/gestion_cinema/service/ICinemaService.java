package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.CinemaDto;

import java.util.List;

public interface ICinemaService {
    CinemaDto getCinemaById(Long cinemaId);
    List<CinemaDto> getAllCinemas();
    CinemaDto createCinema(CinemaDto cinemaDto);
    CinemaDto updateCinema(Long cinemaId, CinemaDto cinemaDto);
    void deleteCinema(Long cinemaId);
}
