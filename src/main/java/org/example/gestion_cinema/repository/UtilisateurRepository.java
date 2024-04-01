package org.example.gestion_cinema.repository;

import org.example.gestion_cinema.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    @Query("SELECT u FROM Utilisateur u WHERE u.nomUtilisateur = ?1 and u.password = ?2")
    Utilisateur findUserByUsernameAndPassword(String username, String password);
}
