package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface ProjectionRepository extends JpaRepository<Projection,Long> {
}
