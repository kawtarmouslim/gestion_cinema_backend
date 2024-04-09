package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;



public interface CinemaRepository extends JpaRepository<Cinema,Long> {
}
