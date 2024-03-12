package org.example.gestion_cinema.repository;

import org.example.gestion_cinema.entites.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Clients,Long> {
}
