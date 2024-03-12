package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.UtilisateurDto;
import org.example.gestion_cinema.service.IClientService;
import org.example.gestion_cinema.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private IClientService iClientService;
    @Autowired
    private IUtilisateurService iUtilisateurService;
    @GetMapping
    public ResponseEntity<List<ClientsDto>> getAllClient() {
        return ResponseEntity.ok(iClientService.getAllClient());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientsDto> getClientById(@PathVariable(name = "id") Long id) {
        ClientsDto  clientsDto= iClientService.getClientById(id);
        return ResponseEntity.ok(clientsDto);
    }
    @PostMapping
    public ResponseEntity<ClientsDto> createClient(@RequestBody ClientsDto clientsDto) {
        ClientsDto nouveauClient = iClientService.createClient(clientsDto);
        return new ResponseEntity<>(nouveauClient, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientsDto> updateClient(@PathVariable Long id, @RequestBody ClientsDto clientsDto) {
        ClientsDto clientMaj = iClientService.updateClient(id, clientsDto);
        return ResponseEntity.ok(clientMaj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        iClientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
