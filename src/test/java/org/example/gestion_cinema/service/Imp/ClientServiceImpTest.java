package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.CinemaDto;
import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.entites.Cinema;
import org.example.gestion_cinema.entites.Clients;
import org.example.gestion_cinema.repository.CinemaRepository;
import org.example.gestion_cinema.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ClientServiceImpTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImp clientService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAllClient() {
        List<Clients> clients = new ArrayList<>();
        clients.add(new Clients());
        clients.add(new Clients());

        List<ClientsDto> clientsDtos = new ArrayList<>();
        clientsDtos.add(new ClientsDto());
        clientsDtos.add(new ClientsDto());

        when(clientRepository.findAll()).thenReturn(clients);
        when(modelMapper.map(clients.get(0), ClientsDto.class)).thenReturn(clientsDtos.get(0));
        when(modelMapper.map(clients.get(1), ClientsDto.class)).thenReturn(clientsDtos.get(1));

        List<ClientsDto> result = clientService.getAllClient();

        assertEquals(clientsDtos.size(), result.size());

    }

    @Test
    void getClientById() {
        Long clientId = 1L;
       Clients clients = new Clients();
        clients.setId(clientId);
        clients.setNom("kawtar");

        ClientsDto clientsDto = new ClientsDto();
        clientsDto.setId(clientId);
        clientsDto.setNom("kawtar");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(clients));
        when(modelMapper.map(clients, ClientsDto.class)).thenReturn(clientsDto);

        ClientsDto result = clientService.getClientById(clientId);

        assertNotNull(result);
        assertEquals(clientId, result.getId());
        assertEquals("kawtar", result.getNom());

        verify(clientRepository, times(1)).findById(clientId);


    }

    @Test
    void createClient() {
        ClientsDto clientsDto = new ClientsDto();
        clientsDto.setId(1L);
        clientsDto.setNom("kawtar");

        Clients clients = new Clients();
        clients.setId(1L);
        clients.setNom("kawtar");

        when(modelMapper.map(clientsDto, Clients.class)).thenReturn(clients);
        when(clientRepository.save(clients)).thenReturn(clients);
        when(modelMapper.map(clients, ClientsDto.class)).thenReturn(clientsDto);

        ClientsDto result = clientService.createClient(clientsDto);

        assertEquals(clientsDto.getId(), result.getId());
        assertEquals(clientsDto.getNom(), result.getNom());
    }

    @Test
    void updateClient() {
        Long id = 1L;
        Clients existingClient = new Clients();
        ClientsDto clientsDto = new ClientsDto();
        Clients updatedClient = new Clients();
        ClientsDto expectedDto = new ClientsDto();
        ClientRepository clientRepository = mock(ClientRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(clientRepository.findById(id)).thenReturn(Optional.of(existingClient));
        when(modelMapper.map(clientsDto, Clients.class)).thenReturn(updatedClient);

        when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

        when(modelMapper.map(updatedClient, ClientsDto.class)).thenReturn(expectedDto);

        ClientServiceImp clientService = new ClientServiceImp(clientRepository, modelMapper);
        ClientsDto result = clientService.updateClient(id, clientsDto);

        assertNotNull(result);
        assertEquals(expectedDto, result);
    }


    @Test
    void deleteClient() {
        Long clientId = 1L;

        when(clientRepository.existsById(clientId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            clientService.deleteClient(clientId);
        });

        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
