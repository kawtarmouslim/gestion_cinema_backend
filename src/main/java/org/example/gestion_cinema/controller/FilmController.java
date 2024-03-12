package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.FilmDto;
import org.example.gestion_cinema.service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private  IFilmService filmService;
    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilms() {
        List<FilmDto> films = filmService.getAllFilms();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable Long filmId) {
        FilmDto film = filmService.getFilmById(filmId);
        return film != null
                ? new ResponseEntity<>(film, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto) {
        FilmDto createdFilm = filmService.createFilm(filmDto);
        return new ResponseEntity<>(createdFilm, HttpStatus.CREATED);
    }

    @PutMapping("/{filmId}")
    public ResponseEntity<FilmDto> updateFilm(@PathVariable Long filmId, @RequestBody FilmDto filmDto) {
        FilmDto updatedFilm = filmService.updateFilm(filmId, filmDto);
        return updatedFilm != null
                ? new ResponseEntity<>(updatedFilm, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long filmId) {
        filmService.deleteFilm(filmId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
