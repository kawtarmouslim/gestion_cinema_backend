package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.entites.Ticket;
import org.example.gestion_cinema.repository.TicketRepository;
import org.example.gestion_cinema.service.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        Ticket savedTicket = ticketRepository.save(ticket);
        return modelMapper.map(savedTicket, TicketDto.class);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }
}

