package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
public interface SeanceRepository extends JpaRepository<Seance,Long> {
}
