package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.CinemaDto;
import org.example.gestion_cinema.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {
    @Autowired
    private ICinemaService cinemaService;

    @GetMapping("/{cinemaId}")
    public ResponseEntity<CinemaDto> getCinemaById(@PathVariable Long cinemaId) {
        CinemaDto cinemaDto = cinemaService.getCinemaById(cinemaId);
        return ResponseEntity.ok(cinemaDto);
    }

    @GetMapping
    public ResponseEntity<List<CinemaDto>> getAllCinemas() {
        List<CinemaDto> cinemas = cinemaService.getAllCinemas();
        return ResponseEntity.ok(cinemas);
    }

    @PostMapping
    public ResponseEntity<CinemaDto> createCinema(@RequestBody CinemaDto cinemaDto) {
        CinemaDto createdCinema = cinemaService.createCinema(cinemaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCinema);
    }

    @PutMapping("/{cinemaId}")
    public ResponseEntity<CinemaDto> updateCinema(@PathVariable Long cinemaId, @RequestBody CinemaDto cinemaDto) {
        CinemaDto updatedCinema = cinemaService.updateCinema(cinemaId, cinemaDto);
        return ResponseEntity.ok(updatedCinema);
    }

    @DeleteMapping("/{cinemaId}")
    public ResponseEntity<Void> deleteCinema(@PathVariable Long cinemaId) {
        cinemaService.deleteCinema(cinemaId);
        return ResponseEntity.noContent().build();
    }

}
