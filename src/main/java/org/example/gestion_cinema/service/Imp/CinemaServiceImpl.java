package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.CinemaDto;
import org.example.gestion_cinema.entites.Cinema;
import org.example.gestion_cinema.entites.Salle;
import org.example.gestion_cinema.entites.Ville;
import org.example.gestion_cinema.repository.CinemaRepository;
import org.example.gestion_cinema.service.ICinemaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements ICinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CinemaDto getCinemaById(Long cinemaId) {
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(cinemaId);
        if (cinemaOptional.isPresent()) {
            return modelMapper.map(cinemaOptional.get(), CinemaDto.class);
        } else {
            throw new EntityNotFoundException("Cinema not found with id: " + cinemaId);
        }
    }

    @Override
    public List<CinemaDto> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        return cinemas.stream()
                .map(cinema -> modelMapper.map(cinema, CinemaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CinemaDto createCinema(CinemaDto cinemaDto) {
        Cinema cinema = modelMapper.map(cinemaDto, Cinema.class);
        Cinema savedCinema = cinemaRepository.save(cinema);
        return modelMapper.map(savedCinema, CinemaDto.class);
    }

    @Override
    public CinemaDto updateCinema(Long cinemaId, CinemaDto cinemaDto) {
        return null;
    }

    @Override
    public void deleteCinema(Long cinemaId) {
        if (cinemaRepository.existsById(cinemaId)) {
            cinemaRepository.deleteById(cinemaId);
        } else {
            throw new EntityNotFoundException("Cinema not found with id: " + cinemaId);
        }
    }
}
