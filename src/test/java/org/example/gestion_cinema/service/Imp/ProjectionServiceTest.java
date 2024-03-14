package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.ProjectionDto;
import org.example.gestion_cinema.entites.Projection;
import org.example.gestion_cinema.repository.ProjectionRepository;
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
import static org.mockito.Mockito.when;

class ProjectionServiceTest {
    private ProjectionRepository projectionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProjectionService projectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllProjections() {


    }

    @Test
    void createProjection() {


    }

    @Test
    void updateProjection() {

    }
}
