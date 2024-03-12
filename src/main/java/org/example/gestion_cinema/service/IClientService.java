package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.ClientsDto;

import java.util.List;

public interface IClientService {
    List<ClientsDto> getAllClient();
    ClientsDto getClientById(Long id);
    ClientsDto createClient(ClientsDto clientsDto);
    ClientsDto updateClient(Long id, ClientsDto clientsDto);
    void deleteClient(Long id);
}
