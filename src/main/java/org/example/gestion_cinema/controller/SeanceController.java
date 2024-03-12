package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.SeanceDto;
import org.example.gestion_cinema.service.ISeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceController {
    @Autowired
    private ISeanceService seanceService;

    @PostMapping
    public ResponseEntity<SeanceDto> createSeance(@RequestBody SeanceDto seanceDto) {
        SeanceDto createdSeance = seanceService.createSeance(seanceDto);
        return new ResponseEntity<>(createdSeance, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeanceDto> getSeanceById(@PathVariable Long id) {
        SeanceDto seanceDto = seanceService.getSeanceById(id);
        if (seanceDto != null) {
            return new ResponseEntity<>(seanceDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SeanceDto>> getAllSeances() {
        List<SeanceDto> allSeances = seanceService.getAllSeances();
        return new ResponseEntity<>(allSeances, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeance(@PathVariable Long id) {
        seanceService.deleteSeance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
