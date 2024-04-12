package org.example.gestion_cinema.repository;

import org.example.gestion_cinema.entites.ERole;
import org.example.gestion_cinema.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
