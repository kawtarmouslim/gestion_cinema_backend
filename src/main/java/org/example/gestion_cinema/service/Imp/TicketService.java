package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.ProjectionDto;
import org.example.gestion_cinema.dtos.TicketDto;
import org.example.gestion_cinema.entites.Clients;
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

import javax.persistence.EntityNotFoundException;
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
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        double prixBase = ticket.getProjection().getPrix();
        double prixPlace = ticket.getPlace().getRangee().equals("VIP") ? prixBase * 1.5 : prixBase;
        ticket.setPrix(prixPlace);
        Place place = ticket.getPlace();
        place.setEstReservee(true);
        Ticket savedTicket = ticketRepository.save(ticket);
        return modelMapper.map(savedTicket, TicketDto.class);

    }


    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto updateTicket(Long ticketId, TicketDto ticketDto) {
        Ticket existingTicket= ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket non trouvé avec l'ID : " + ticketId));
        modelMapper.typeMap(TicketDto.class, Ticket.class)
                .addMappings(mapper -> mapper.skip(Ticket::setId));

        modelMapper.map(ticketDto, existingTicket);
        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return modelMapper.map(updatedTicket, TicketDto.class);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new EntityNotFoundException("ticket non trouvé avec l'ID : " + ticketId);
        }
        ticketRepository.deleteById(ticketId);

    }

    private TicketDto convertToDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }
}

