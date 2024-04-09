package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private ITicketService ticketService;
    @PostMapping("/add")
    public ResponseEntity<TicketDto> creerTicket(@RequestBody TicketDto ticketDto) {
        TicketDto nouveauBillet = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(nouveauBillet, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> allTickets = ticketService.getAllTickets();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

}
