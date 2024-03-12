package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.UtilisateurDto;
import org.example.gestion_cinema.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private IUtilisateurService iUtilisateurService;
    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateur() {
        return ResponseEntity.ok(iUtilisateurService.getAllUtilisateut());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable(name = "id") Long id) {
        UtilisateurDto utilisateurDto= iUtilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(utilisateurDto);
    }
    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto livreDto) {
        UtilisateurDto nouveauUtilisateur = iUtilisateurService.createUtilisateur(livreDto);
        return new ResponseEntity<>(nouveauUtilisateur, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto utilisateurMaj = iUtilisateurService.updateLivre(id, utilisateurDto);
        return ResponseEntity.ok(utilisateurMaj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        iUtilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
