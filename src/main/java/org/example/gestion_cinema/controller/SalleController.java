package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.SalleDto;
import org.example.gestion_cinema.service.ISalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {
    @Autowired
    private ISalleService salleService;

    @GetMapping("/{salleId}")
    public ResponseEntity<SalleDto> getSalleById(@PathVariable Long salleId) {
        SalleDto salleDto = salleService.getSalleById(salleId);
        return new ResponseEntity<>(salleDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SalleDto>> getAllSalles() {
        List<SalleDto> salles = salleService.getAllSalles();
        return new ResponseEntity<>(salles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalleDto> createSalle(@RequestBody SalleDto salleDto) {
        SalleDto createdSalle = salleService.createSalle(salleDto);
        return new ResponseEntity<>(createdSalle, HttpStatus.CREATED);
    }

    @PutMapping("/{salleId}")
    public ResponseEntity<SalleDto> updateSalle(@PathVariable Long salleId, @RequestBody SalleDto salleDto) {
        SalleDto updatedSalle = salleService.updateSalle(salleId, salleDto);
        return new ResponseEntity<>(updatedSalle, HttpStatus.OK);
    }

    @DeleteMapping("/{salleId}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long salleId) {
        salleService.deleteSalle(salleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
