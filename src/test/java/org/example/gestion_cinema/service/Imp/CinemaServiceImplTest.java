package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.CinemaDto;
import org.example.gestion_cinema.entites.Cinema;
import org.example.gestion_cinema.repository.CinemaRepository;
import org.example.gestion_cinema.service.ICinemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CinemaServiceImplTest {



    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CinemaServiceImpl cinemaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCinemaById() {
        Long cinemaId = 1L;
        Cinema cinema = new Cinema();
        cinema.setId(cinemaId);
        cinema.setName("Cinema Test");

        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(cinemaId);
        cinemaDto.setName("Cinema Test");

        when(cinemaRepository.findById(cinemaId)).thenReturn(Optional.of(cinema));
        when(modelMapper.map(cinema, CinemaDto.class)).thenReturn(cinemaDto);

        CinemaDto result = cinemaService.getCinemaById(cinemaId);

        assertNotNull(result);
        assertEquals(cinemaId, result.getId());
        assertEquals("Cinema Test", result.getName());

        verify(cinemaRepository, times(1)).findById(cinemaId);

    }



    @Test
    void getAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(new Cinema());
        cinemas.add(new Cinema());

        List<CinemaDto> cinemaDtos = new ArrayList<>();
        cinemaDtos.add(new CinemaDto());
        cinemaDtos.add(new CinemaDto());

        when(cinemaRepository.findAll()).thenReturn(cinemas);
        when(modelMapper.map(cinemas.get(0), CinemaDto.class)).thenReturn(cinemaDtos.get(0));
        when(modelMapper.map(cinemas.get(1), CinemaDto.class)).thenReturn(cinemaDtos.get(1));

        List<CinemaDto> result = cinemaService.getAllCinemas();

        assertEquals(cinemaDtos.size(), result.size());
    }

    @Test
    void createCinema() {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(1L);
        cinemaDto.setName("Cinema Test");

        Cinema cinema = new Cinema();
        cinema.setId(1L);
        cinema.setName("Cinema Test");

        when(modelMapper.map(cinemaDto, Cinema.class)).thenReturn(cinema);
        when(cinemaRepository.save(cinema)).thenReturn(cinema);
        when(modelMapper.map(cinema, CinemaDto.class)).thenReturn(cinemaDto);

        CinemaDto result = cinemaService.createCinema(cinemaDto);

        assertEquals(cinemaDto.getId(), result.getId());
        assertEquals(cinemaDto.getName(), result.getName());

    }


    @Test
    void deleteCinema() {
        Long cinemaId = 1L;

        when(cinemaRepository.existsById(cinemaId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            cinemaService.deleteCinema(cinemaId);
        });

        verify(cinemaRepository, times(1)).deleteById(cinemaId);
    }
}
