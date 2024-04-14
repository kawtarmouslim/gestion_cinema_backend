package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.ProjectionDto;
import org.example.gestion_cinema.entites.Film;
import org.example.gestion_cinema.entites.Projection;
import org.example.gestion_cinema.entites.Salle;
import org.example.gestion_cinema.repository.ProjectionRepository;
import org.example.gestion_cinema.service.IProjectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectionService implements IProjectionService {
    @Autowired
    private ProjectionRepository projectionRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<ProjectionDto> getAllProjections() {
        List<Projection> projections = projectionRepository.findAll();
        return projections.stream()
                .map(projection -> modelMapper.map(projection, ProjectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectionDto createProjection(ProjectionDto projectionDto) {
        Projection projection = modelMapper.map(projectionDto, Projection.class);
        Projection savedProjection = projectionRepository.save(projection);
        return modelMapper.map(savedProjection, ProjectionDto.class);
    }

    @Override
    public ProjectionDto updateProjection(Long projectionId, ProjectionDto projectionDto) {
        Optional<Projection> existingProjectionOptional = projectionRepository.findById(projectionId);
        if (existingProjectionOptional.isPresent()) {
            Projection existingProjection = existingProjectionOptional.get();
            existingProjection.setDateProjection(projectionDto.getDateProjection());
            existingProjection.setPrix(projectionDto.getPrix());
            if (projectionDto.getSalle() != null) {
                existingProjection.setSalle(modelMapper.map(projectionDto.getSalle(), Salle.class));
            }
            if (projectionDto.getFilm() != null) {
                existingProjection.setFilm(modelMapper.map(projectionDto.getFilm(), Film.class));
            }
            Projection updatedProjection = projectionRepository.save(existingProjection);
            return modelMapper.map(updatedProjection, ProjectionDto.class);
        } else {
            throw new EntityNotFoundException("Projection not found with id: " + projectionId);
        }
    }

    @Override
    public ProjectionDto getProjectionById(Long id) {
        Optional<Projection> projectionOptional = projectionRepository.findById(id);
        return projectionOptional.map(projection -> modelMapper.map(projection, ProjectionDto.class)).orElse(null);
    }

    @Override
    public void deleteProjection(Long projectionId) {
        if (!projectionRepository.existsById(projectionId)) {
            throw new EntityNotFoundException("projection non trouvé avec l'ID : " + projectionId);
        }
        projectionRepository.deleteById(projectionId);
    }

}

