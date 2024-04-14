package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.CinemaDto;
import org.example.gestion_cinema.dtos.PlaceDto;

import java.util.List;

public interface IPlaceService {
    List<PlaceDto> getAllPlaces();


    PlaceDto createPlace(PlaceDto placeDto);

    PlaceDto updatePlace(Long placeId, PlaceDto placeDto);

    void deletePlace(Long placeId);

    PlaceDto getPlaceById(Long id);
}
