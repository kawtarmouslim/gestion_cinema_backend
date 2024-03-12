package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.FilmDto;

import java.util.List;

public interface IFilmService {
    FilmDto getFilmById(Long filmId);

    List<FilmDto> getAllFilms();

    FilmDto createFilm(FilmDto filmDto);

    FilmDto updateFilm(Long filmId, FilmDto filmDto);

    void deleteFilm(Long filmId);
}
