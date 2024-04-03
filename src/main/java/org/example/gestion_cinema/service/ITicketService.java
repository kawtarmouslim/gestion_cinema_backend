package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.TicketDto;

import java.util.List;

public interface ITicketService {
    TicketDto createTicket(TicketDto ticketDto);
    List<TicketDto> getAllTickets();
    TicketDto updateTicket(Long ticketId, TicketDto ticketDto);
    void deleteTicket(Long ticketId);
    TicketDto getTicketById(Long id);

}
