package org.example.gestion_cinema.repository;

import org.example.gestion_cinema.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
}
