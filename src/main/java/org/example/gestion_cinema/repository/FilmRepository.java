package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
public interface FilmRepository extends JpaRepository<Film,Long> {
}
