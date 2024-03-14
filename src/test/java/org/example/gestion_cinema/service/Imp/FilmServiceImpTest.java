package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.FilmDto;
import org.example.gestion_cinema.entites.Film;
import org.example.gestion_cinema.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmServiceImpTest {
    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FilmServiceImp filmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllFilms() {
        List<Film> films = new ArrayList<>();
        films.add(new Film());
        films.add(new Film());

        List<FilmDto> filmsDto = new ArrayList<>();
        filmsDto.add(new FilmDto());
        filmsDto.add(new FilmDto());

        when(filmRepository.findAll()).thenReturn(films);
        when(modelMapper.map(films.get(0), FilmDto.class)).thenReturn(filmsDto.get(0));
        when(modelMapper.map(films.get(1), FilmDto.class)).thenReturn(filmsDto.get(1));

        List<FilmDto> result = filmService.getAllFilms();

        assertEquals(filmsDto.size(), result.size());
    }

    @Test
    void getFilmById() {
        Long filmId = 1L;
        Film film = new Film();
        film.setId(filmId);
        film.setTitre("Film 1");

        FilmDto filmDto = new FilmDto();
        filmDto.setId(filmId);
        filmDto.setTitre("Film 1");

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(modelMapper.map(film, FilmDto.class)).thenReturn(filmDto);

        FilmDto result = filmService.getFilmById(filmId);

        assertNotNull(result);
        assertEquals(filmId, result.getId());
        assertEquals("Film 1", result.getTitre());

        verify(filmRepository, times(1)).findById(filmId);
    }

    @Test
    void createFilm() {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(1L);
        filmDto.setTitre("Film 1");

        Film film = new Film();
        film.setId(1L);
        film.setTitre("Film 1");

        when(modelMapper.map(filmDto, Film.class)).thenReturn(film);
        when(filmRepository.save(film)).thenReturn(film);
        when(modelMapper.map(film, FilmDto.class)).thenReturn(filmDto);

        FilmDto result = filmService.createFilm(filmDto);

        assertEquals(filmDto.getId(), result.getId());
        assertEquals(filmDto.getTitre(), result.getTitre());
    }

    @Test
    void updateFilm() {
        Long id = 1L;
        Film existingFilm = new Film();
        FilmDto filmDto = new FilmDto();
        Film updatedFilm = new Film();
        FilmDto expectedDto = new FilmDto();

        when(filmRepository.findById(id)).thenReturn(Optional.of(existingFilm));
        when(modelMapper.map(filmDto, Film.class)).thenReturn(updatedFilm);
        when(filmRepository.save(updatedFilm)).thenReturn(updatedFilm);
        when(modelMapper.map(updatedFilm, FilmDto.class)).thenReturn(expectedDto);

        FilmDto result = filmService.updateFilm(id, filmDto);

        assertNotNull(result);
        assertEquals(expectedDto, result);
    }

    @Test
    void deleteFilm() {
        Long filmId = 1L;

        when(filmRepository.existsById(filmId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            filmService.deleteFilm(filmId);
        });

        verify(filmRepository, times(1)).deleteById(filmId);
    }
}
