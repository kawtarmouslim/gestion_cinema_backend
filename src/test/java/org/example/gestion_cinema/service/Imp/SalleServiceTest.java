package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.SalleDto;
import org.example.gestion_cinema.entites.Salle;
import org.example.gestion_cinema.repository.SalleRepository;
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

class SalleServiceTest {
    @Mock
    private SalleRepository salleRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SalleService salleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getSalleById() {
        Long salleId = 1L;
        Salle salle = new Salle();
        salle.setId(salleId);
        salle.setName("Salle 1");

        SalleDto salleDto = new SalleDto();
        salleDto.setId(salleId);
        salleDto.setName("Salle 1");

        when(salleRepository.findById(salleId)).thenReturn(Optional.of(salle));
        when(modelMapper.map(salle, SalleDto.class)).thenReturn(salleDto);

        SalleDto result = salleService.getSalleById(salleId);

        assertNotNull(result);
        assertEquals(salleId, result.getId());
        assertEquals("Salle 1", result.getName());

        verify(salleRepository, times(1)).findById(salleId);
    }

    @Test
    void getAllSalles() {
        List<Salle> salles = new ArrayList<>();
        salles.add(new Salle());
        salles.add(new Salle());

        List<SalleDto> sallesDto = new ArrayList<>();
        sallesDto.add(new SalleDto());
        sallesDto.add(new SalleDto());

        when(salleRepository.findAll()).thenReturn(salles);
        when(modelMapper.map(salles.get(0), SalleDto.class)).thenReturn(sallesDto.get(0));
        when(modelMapper.map(salles.get(1), SalleDto.class)).thenReturn(sallesDto.get(1));

        List<SalleDto> result = salleService.getAllSalles();

        assertEquals(sallesDto.size(), result.size());
    }

    @Test
    void createSalle() {
        SalleDto salleDto = new SalleDto();
        salleDto.setId(1L);
        salleDto.setName("Salle 1");

        Salle salle = new Salle();
        salle.setId(1L);
        salle.setName("Salle 1");

        when(modelMapper.map(salleDto, Salle.class)).thenReturn(salle);
        when(salleRepository.save(salle)).thenReturn(salle);
        when(modelMapper.map(salle, SalleDto.class)).thenReturn(salleDto);

        SalleDto result = salleService.createSalle(salleDto);

        assertEquals(salleDto.getId(), result.getId());
        assertEquals(salleDto.getName(), result.getName());
    }

    @Test
    void updateSalle() {
        Long id = 1L;
        Salle existingSalle = new Salle();
        SalleDto salleDto = new SalleDto();
        Salle updatedSalle = new Salle();
        SalleDto expectedDto = new SalleDto();

        when(salleRepository.findById(id)).thenReturn(Optional.of(existingSalle));
        when(modelMapper.map(salleDto, Salle.class)).thenReturn(updatedSalle);
        when(salleRepository.save(updatedSalle)).thenReturn(updatedSalle);
        when(modelMapper.map(updatedSalle, SalleDto.class)).thenReturn(expectedDto);

        SalleDto result = salleService.updateSalle(id, salleDto);

        assertNotNull(result);
        assertEquals(expectedDto, result);

    }

    @Test
    void deleteSalle() {
        Long salleId = 1L;

        when(salleRepository.existsById(salleId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            salleService.deleteSalle(salleId);
        });

    }
}
