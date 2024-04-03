package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.FilmDto;
import org.example.gestion_cinema.dtos.ProjectionDto;
import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.entites.*;
import org.example.gestion_cinema.repository.PlaceRepository;
import org.example.gestion_cinema.repository.ProjectionRepository;
import org.example.gestion_cinema.repository.TicketRepository;
import org.example.gestion_cinema.service.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        double prixBase = ticket.getProjection().getPrix();
        double prixPlace = ticket.getPlace().getRangee().equals("VIP") ? prixBase * 1.5 : prixBase;
        ticket.setPrix(prixPlace);
        Place place = ticket.getPlace();
        place.setEstReservee(true); // Marquer la place comme réservée
        // Assurez-vous que la place est mise à jour dans la base de données si nécessaire
        // par exemple, placeRepository.save(place);
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

    @Override
    public TicketDto updateTicket(Long ticketId, TicketDto ticketDto) {
        // Vérifier si le ticket existe dans la base de données
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setClient(modelMapper.map(ticketDto.getClient(), Clients.class));
            ticket.setPlace(modelMapper.map(ticketDto.getPlace(), Place.class));
            ticket.setProjection(modelMapper.map(ticketDto.getProjection(), Projection.class));
            Ticket updatedTicket = ticketRepository.save(ticket);
            return modelMapper.map(updatedTicket, TicketDto.class);
        } else {
            throw new EntityNotFoundException("Ticket non trouvé avec l'ID: " + ticketId);
        }
    }

    @Override
    public void deleteTicket(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
        } else {
            throw new EntityNotFoundException("Ticket non trouvé avec l'ID: " + ticketId);
        }
    }

    @Override
    public TicketDto getTicketById(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        return ticketOptional.map(ticket -> modelMapper.map(ticket, TicketDto.class)).orElse(null);
    }
}

