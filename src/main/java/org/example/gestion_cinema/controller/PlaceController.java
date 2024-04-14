package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.PlaceDto;
import org.example.gestion_cinema.dtos.SalleDto;
import org.example.gestion_cinema.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/places")
public class PlaceController {
    @Autowired
    private IPlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceDto> creerPlace(@RequestBody PlaceDto placeDto) {
        PlaceDto placeCree = placeService.createPlace(placeDto);
        return new ResponseEntity<>(placeCree, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<PlaceDto>> getAllNonReservedPlaces() {
        List<PlaceDto> places = placeService.getAllPlaces();
        if(places.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retourne 204 No Content si la liste est vide
        }
        return new ResponseEntity<>(places, HttpStatus.OK); // Retourne 200 OK avec la liste des places
    }
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable Long placeId) {
        PlaceDto placeDto = placeService.getPlaceById(placeId);
        return new ResponseEntity<>(placeDto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlaceDto> updatePlace(@PathVariable Long id, @RequestBody PlaceDto placeDto) {
        PlaceDto placeMaj = placeService.updatePlace(id, placeDto);
        return ResponseEntity.ok(placeMaj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    }



