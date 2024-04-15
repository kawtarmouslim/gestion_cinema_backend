package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.FilmDto;
import org.example.gestion_cinema.entites.Clients;
import org.example.gestion_cinema.entites.Film;
import org.example.gestion_cinema.repository.FilmRepository;
import org.example.gestion_cinema.service.IFilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImp implements IFilmService {
    @Autowired
    private  FilmRepository filmRepository;
    @Autowired
    private  ModelMapper modelMapper;
    @Override
    public List<FilmDto> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(film -> modelMapper.map(film, FilmDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FilmDto getFilmById(Long filmId) {
        Optional<Film> filmOptional = filmRepository.findById(filmId);
        return filmOptional.map(film -> modelMapper.map(film, FilmDto.class)).orElse(null);
    }

    @Override
    public FilmDto createFilm(FilmDto filmDto) {
        Film film = modelMapper.map(filmDto, Film.class);
        Film savedFilm = filmRepository.save(film);
        return modelMapper.map(savedFilm, FilmDto.class);
    }

    @Override
    public FilmDto updateFilm(Long filmId, FilmDto filmDto) {
        Optional<Film> existingFilmOptional = filmRepository.findById(filmId);
        if (existingFilmOptional.isPresent()) {
            Film existingFilm = existingFilmOptional.get();
            existingFilm.setTitre(filmDto.getTitre());
            existingFilm.setGenre(filmDto.getGenre());
            existingFilm.setDateFilm(filmDto.getDateFilm());
            existingFilm.setGenre(filmDto.getGenre());
            existingFilm.setCheminImage(filmDto.getCheminImage());

            Film updatedFilm = filmRepository.save(existingFilm);
            return modelMapper.map(updatedFilm, FilmDto.class);
        } else {
            throw new EntityNotFoundException("fILM not found with id: " + filmId);
        }
    }
    public void deleteFilm(Long filmId) {

        if (filmRepository.existsById(filmId)) {
            filmRepository.deleteById(filmId);
        } else {
            throw new EntityNotFoundException("Film not found with id: " + filmId);
        }
    }
}
