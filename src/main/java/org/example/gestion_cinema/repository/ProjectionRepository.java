package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
public interface ProjectionRepository extends JpaRepository<Projection,Long> {
}
