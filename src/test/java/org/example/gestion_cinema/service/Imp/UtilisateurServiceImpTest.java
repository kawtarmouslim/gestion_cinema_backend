package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.UtilisateurDto;
import org.example.gestion_cinema.entites.Utilisateur;
import org.example.gestion_cinema.repository.UtilisateurRepository;
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

class UtilisateurServiceImpTest {
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UtilisateurServiceImp utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAllUtilisateut() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(new Utilisateur());
        utilisateurs.add(new Utilisateur());

        List<UtilisateurDto> utilisateursDto = new ArrayList<>();
        utilisateursDto.add(new UtilisateurDto());
        utilisateursDto.add(new UtilisateurDto());

        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);
        when(modelMapper.map(utilisateurs.get(0), UtilisateurDto.class)).thenReturn(utilisateursDto.get(0));
        when(modelMapper.map(utilisateurs.get(1), UtilisateurDto.class)).thenReturn(utilisateursDto.get(1));

        List<UtilisateurDto> result = utilisateurService.getAllUtilisateut();

        assertEquals(utilisateursDto.size(), result.size());
    }

    @Test
    void getUtilisateurById() {
        Long utilisateurId = 1L;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurId);
        utilisateur.setNom("kawtar");

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(utilisateurId);
        utilisateurDto.setNom("kawtar");

        when(utilisateurRepository.findById(utilisateurId)).thenReturn(Optional.of(utilisateur));
        when(modelMapper.map(utilisateur, UtilisateurDto.class)).thenReturn(utilisateurDto);

        UtilisateurDto result = utilisateurService.getUtilisateurById(utilisateurId);

        assertNotNull(result);
        assertEquals(utilisateurId, result.getId());
        assertEquals("kawtar", result.getNom());

        verify(utilisateurRepository, times(1)).findById(utilisateurId);
    }

    @Test
    void createUtilisateur() {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setNom("kawtar");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("kawtar");

        when(modelMapper.map(utilisateurDto, Utilisateur.class)).thenReturn(utilisateur);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        when(modelMapper.map(utilisateur, UtilisateurDto.class)).thenReturn(utilisateurDto);

        UtilisateurDto result = utilisateurService.createUtilisateur(utilisateurDto);

        assertEquals(utilisateurDto.getId(), result.getId());
        assertEquals(utilisateurDto.getNom(), result.getNom());
    }

    @Test
    void updateLivre() {
        Long id = 1L;
        Utilisateur existingUtilisateur = new Utilisateur();
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        Utilisateur updatedUtilisateur = new Utilisateur();
        UtilisateurDto expectedDto = new UtilisateurDto();

        // Comportement des mocks
        UtilisateurRepository utilisateurRepository = mock(UtilisateurRepository.class);
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(utilisateurRepository.findById(id)).thenReturn(Optional.of(existingUtilisateur));
        when(modelMapper.map(utilisateurDto, Utilisateur.class)).thenReturn(updatedUtilisateur);
        when(utilisateurRepository.save(updatedUtilisateur)).thenReturn(updatedUtilisateur);
        when(modelMapper.map(updatedUtilisateur, UtilisateurDto.class)).thenReturn(expectedDto);

        UtilisateurServiceImp utilisateurService = new UtilisateurServiceImp(utilisateurRepository, modelMapper);
        UtilisateurDto result = utilisateurService.updateLivre(id, utilisateurDto);

        assertNotNull(result);
        assertEquals(expectedDto, result);
    }

    @Test
    void deleteUtilisateur() {
        Long utilisateurId = 1L;

        when(utilisateurRepository.existsById(utilisateurId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            utilisateurService.deleteUtilisateur(utilisateurId);
        });

        verify(utilisateurRepository, times(1)).deleteById(utilisateurId);

    }
}
