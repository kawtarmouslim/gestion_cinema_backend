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
    void deleteSeance() {
        Long seanceId = 1L;

        assertDoesNotThrow(() -> {
            seanceService.deleteSeance(seanceId);
        });

        verify(seanceRepository, times(1)).deleteById(seanceId);
    }
}
