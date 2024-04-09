package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.PlaceDto;
import org.example.gestion_cinema.dtos.SalleDto;
import org.example.gestion_cinema.entites.Place;
import org.example.gestion_cinema.entites.Salle;
import org.example.gestion_cinema.repository.PlaceRepository;
import org.example.gestion_cinema.service.IPlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceService implements IPlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlaceDto> getAllPlaces() {
        return placeRepository.findAll().stream()
                .filter(place -> !place.isEstReservee()) // Filtrer les places non réservées
                .map(place -> modelMapper.map(place, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceDto createPlace(PlaceDto placeDto) {
        Place place = modelMapper.map(placeDto, Place.class);
        Place savedPlace = placeRepository.save(place);
        return modelMapper.map(savedPlace, PlaceDto.class);
    }

    @Override
    public PlaceDto updatePlace(Long placeId, PlaceDto placeDto) {
        return null;
    }

    @Override
    public void deletePlace(Long placeId) {

    }

    @Override
    public PlaceDto getPlaceById(Long id) {
        return null;
    }
}

