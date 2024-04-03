package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private ITicketService ticketService;
    @PostMapping("/add")
    public ResponseEntity<TicketDto> creerTicket(@RequestBody TicketDto ticketDto) {
        TicketDto creatrTicket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(creatrTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> allTickets = ticketService.getAllTickets();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long ticketId) {
        TicketDto ticketDto = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticketDto);
    }
    @PutMapping("/update/{ticketId}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDto ticketDto) {
        TicketDto updatedTicket = ticketService.updateTicket(ticketId, ticketDto);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
