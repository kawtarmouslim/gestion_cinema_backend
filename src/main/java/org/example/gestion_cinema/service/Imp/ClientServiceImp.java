package org.example.gestion_cinema.service.Imp;

import lombok.AllArgsConstructor;
import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.SalleDto;
import org.example.gestion_cinema.entites.Clients;
import org.example.gestion_cinema.entites.Salle;
import org.example.gestion_cinema.entites.Utilisateur;
import org.example.gestion_cinema.repository.ClientRepository;
import org.example.gestion_cinema.repository.UtilisateurRepository;
import org.example.gestion_cinema.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImp implements IClientService {
    @Autowired
    private final  ClientRepository clientRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public List<ClientsDto> getAllClient() {
        List<Clients> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientsDto.class)) // Correction ici
                .collect(Collectors.toList());

    }

    @Override
    public ClientsDto getClientById(Long id) {
        Clients clients = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("client non trouvé avec l'ID : " + id));
        return modelMapper.map(clients, ClientsDto.class);

    }

    @Override
    public ClientsDto createClient(ClientsDto clientsDto) {
        Clients clients = modelMapper.map(clientsDto, Clients.class);
        Clients savedClient = clientRepository.save(clients);
        return modelMapper.map(savedClient, ClientsDto.class);
    }

    @Override
    public ClientsDto updateClient(Long id, ClientsDto clientsDto) {

        Optional<Clients> existingClientOptional = clientRepository.findById(id);
        if (existingClientOptional.isPresent()) {
            Clients existingClient = existingClientOptional.get();
            existingClient.setNom(clientsDto.getNom());
            existingClient.setTel(clientsDto.getTel());

            Clients updatedClient = clientRepository.save(existingClient);
            return modelMapper.map(updatedClient, ClientsDto.class);
        } else {
            throw new EntityNotFoundException("CLIENT not found with id: " + id);
        }
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client non trouvé avec l'ID : " + id);
        }
        clientRepository.deleteById(id);

    }


}
