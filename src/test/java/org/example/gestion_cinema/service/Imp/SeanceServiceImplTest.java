package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.SeanceDto;
import org.example.gestion_cinema.entites.Seance;
import org.example.gestion_cinema.repository.SeanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeanceServiceImplTest {
    @Mock
    private SeanceRepository seanceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SeanceServiceImpl seanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createSeance() {
        SeanceDto seanceDto = new SeanceDto();
        seanceDto.setId(1L);
        seanceDto.setHeurDebut(new Date());

        Seance seance = new Seance();
        seance.setId(1L);
        seance.setHeurDebut(new Date());

        when(modelMapper.map(seanceDto, Seance.class)).thenReturn(seance);
        when(seanceRepository.save(seance)).thenReturn(seance);
        when(modelMapper.map(seance, SeanceDto.class)).thenReturn(seanceDto);

        SeanceDto result = seanceService.createSeance(seanceDto);

        assertEquals(seanceDto.getId(), result.getId());
        assertEquals(seanceDto.getHeurDebut(), result.getHeurDebut());
    }

    @Test
    void getSeanceById() {
        Long seanceId = 1L;
        Date heureDebut = new Date();
        Seance seance = new Seance();
        seance.setId(seanceId);
        seance.setHeurDebut(heureDebut);

        SeanceDto seanceDto = new SeanceDto();
        seanceDto.setId(seanceId);
        seanceDto.setHeurDebut(heureDebut);

        when(seanceRepository.findById(seanceId)).thenReturn(Optional.of(seance));
        when(modelMapper.map(seance, SeanceDto.class)).thenReturn(seanceDto);

        SeanceDto result = seanceService.getSeanceById(seanceId);

        assertNotNull(result);
        assertEquals(seanceId, result.getId());
        assertEquals(heureDebut, result.getHeurDebut());

        verify(seanceRepository, times(1)).findById(seanceId);

    }

    @Test
    void getAllSeances() {
        List<Seance> seances = new ArrayList<>();
        seances.add(new Seance());
        seances.add(new Seance());

        List<SeanceDto> seancesDto = new ArrayList<>();
        seancesDto.add(new SeanceDto());
        seancesDto.add(new SeanceDto());

        when(seanceRepository.findAll()).thenReturn(seances);
        when(modelMapper.map(seances.get(0), SeanceDto.class)).thenReturn(seancesDto.get(0));
        when(modelMapper.map(seances.get(1), SeanceDto.class)).thenReturn(seancesDto.get(1));

        List<SeanceDto> result = seanceService.getAllSeances();

        assertEquals(seancesDto.size(), result.size());
    }

    @Test
    void deleteSeance() {
        Long seanceId = 1L;

        assertDoesNotThrow(() -> {
            seanceService.deleteSeance(seanceId);
        });

        verify(seanceRepository, times(1)).deleteById(seanceId);
    }
}
