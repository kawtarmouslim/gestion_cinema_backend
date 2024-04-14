package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
@Repository

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
