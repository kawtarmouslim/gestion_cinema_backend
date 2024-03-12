package org.example.gestion_cinema.repository;


import org.example.gestion_cinema.entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
