package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.ProjectionDto;
import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.entites.Place;
import org.example.gestion_cinema.entites.Projection;
import org.example.gestion_cinema.entites.Ticket;
import org.example.gestion_cinema.repository.PlaceRepository;
import org.example.gestion_cinema.repository.ProjectionRepository;
import org.example.gestion_cinema.repository.TicketRepository;
import org.example.gestion_cinema.service.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        // Mapper l'objet TicketDto avec une entité Ticket
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        // Calculer le prix du ticket en fonction de la place et de la projection
        double prixBase = ticket.getProjection().getPrix(); // Prix de base de la projection
        double prixPlace = ticket.getPlace().getRangee().equals("VIP") ? prixBase * 1.5 : prixBase; // Si la place est VIP, appliquer un coefficient de majoration
        // Modifier le prix du ticket
        ticket.setPrix(prixPlace);
        ticket.setPlace(modelMapper.map(ticketDto.getPlace(), Place.class)); // Mapper la place
        ticket.setProjection(modelMapper.map(ticketDto.getProjection(), Projection.class)); // Mapper la projection
        // Mapper l'entité Ticket modifiée avec un DTO pour retourner
       Ticket savedTicket = ticketRepository.save(ticket);
        return modelMapper.map(savedTicket, TicketDto.class);
  //    return modelMapper.map(ticket, TicketDto.class);
    }


    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }
}

