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

    @PostMapping
    public ResponseEntity<PlaceDto> creerPlace(@RequestBody PlaceDto placeDto) {
        PlaceDto placeCree = placeService.createPlace(placeDto);
        return new ResponseEntity<>(placeCree, HttpStatus.CREATED);
    }
    }



