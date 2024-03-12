package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.VilleDto;
import org.example.gestion_cinema.service.IVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/villes")
public class VilleController {
    @Autowired
    private IVilleService villeService;

    @PostMapping
    public ResponseEntity<VilleDto> createVille(@RequestBody VilleDto villeDto) {
        VilleDto createdVille = villeService.createVille(villeDto);
        return new ResponseEntity<>(createdVille, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VilleDto> getVilleById(@PathVariable Long id) {
        VilleDto villeDto = villeService.getVilleById(id);
        if (villeDto != null) {
            return new ResponseEntity<>(villeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<VilleDto>> getAllVilles() {
        List<VilleDto> allVilles = villeService.getAllVilles();
        return new ResponseEntity<>(allVilles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable Long id) {
        villeService.deleteVille(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
