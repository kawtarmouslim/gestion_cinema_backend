package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.PlaceDto;
import org.example.gestion_cinema.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    @Autowired
    private IPlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        List<PlaceDto> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @PostMapping
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto placeDto) {
        PlaceDto createdPlace = placeService.createPlace(placeDto);
        return new ResponseEntity<>(createdPlace, HttpStatus.CREATED);
    }

    @PutMapping("/{placeId}")
    public ResponseEntity<PlaceDto> updatePlace(@PathVariable Long placeId, @RequestBody PlaceDto placeDto) {
        PlaceDto updatedPlace = placeService.updatePlace(placeId, placeDto);
        return ResponseEntity.ok(updatedPlace);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.noContent().build();
    }
}
