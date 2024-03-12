package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.TicketDto;

import java.util.List;

public interface ITicketService {
    TicketDto createTicket(TicketDto ticketDto);
    List<TicketDto> getAllTickets();

}
