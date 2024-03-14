package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.VilleDto;
import org.example.gestion_cinema.entites.Ville;
import org.example.gestion_cinema.repository.VilleRepository;
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

class VilleServiceImpTest {
    @Mock
    private VilleRepository villeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VilleServiceImp villeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createVille() {
        VilleDto villeDto = new VilleDto();
        villeDto.setId(1L);
        villeDto.setName("casablanca");

        Ville ville = new Ville();
        ville.setId(1L);
        ville.setName("casablanca");

        when(modelMapper.map(villeDto, Ville.class)).thenReturn(ville);
        when(villeRepository.save(ville)).thenReturn(ville);
        when(modelMapper.map(ville, VilleDto.class)).thenReturn(villeDto);

        VilleDto result = villeService.createVille(villeDto);

        assertEquals(villeDto.getId(), result.getId());
        assertEquals(villeDto.getName(), result.getName());
    }

    @Test
    void getVilleById() {
        Long villeId = 1L;
        Ville ville = new Ville();
        ville.setId(villeId);
        ville.setName("casablanca"); // Nom de la ville sans espace en trop

        VilleDto villeDto = new VilleDto();
        villeDto.setId(villeId);
        villeDto.setName("casablanca"); // Nom de la ville sans espace en trop

        when(villeRepository.findById(villeId)).thenReturn(Optional.of(ville));
        when(modelMapper.map(ville, VilleDto.class)).thenReturn(villeDto);

        VilleDto result = villeService.getVilleById(villeId);

        assertNotNull(result);
        assertEquals(villeId, result.getId());
        assertEquals("casablanca", result.getName());

        verify(villeRepository, times(1)).findById(villeId);
    }

    @Test
    void getAllVilles() {
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville());
        villes.add(new Ville());

        List<VilleDto> villesDto = new ArrayList<>();
        villesDto.add(new VilleDto());
        villesDto.add(new VilleDto());

        when(villeRepository.findAll()).thenReturn(villes);
        when(modelMapper.map(villes.get(0), VilleDto.class)).thenReturn(villesDto.get(0));
        when(modelMapper.map(villes.get(1), VilleDto.class)).thenReturn(villesDto.get(1));

        List<VilleDto> result = villeService.getAllVilles();

        assertEquals(villesDto.size(), result.size());
    }

    @Test
    void deleteVille() {
        Long villeId = 1L;

        assertDoesNotThrow(() -> {
            villeService.deleteVille(villeId);
        });

        verify(villeRepository, times(1)).deleteById(villeId);
    }
}
