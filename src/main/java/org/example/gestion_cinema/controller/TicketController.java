package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.service.ITicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(TicketController.class);
    @PostMapping("/add")
    public ResponseEntity<TicketDto> creerTicket(@RequestBody TicketDto ticketDto) {
        TicketDto nouveauBillet = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(nouveauBillet, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> allTickets = ticketService.getAllTickets();
        allTickets.forEach(System.out::println);
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto) {
        TicketDto ticketMaj = ticketService.updateTicket(id, ticketDto);
        return ResponseEntity.ok(ticketMaj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
