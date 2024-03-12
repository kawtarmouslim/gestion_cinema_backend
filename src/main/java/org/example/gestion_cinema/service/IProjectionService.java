package org.example.gestion_cinema.service;

import org.example.gestion_cinema.dtos.ProjectionDto;

import java.util.List;

public interface IProjectionService {
    List<ProjectionDto> getAllProjections();

    ProjectionDto createProjection(ProjectionDto projectionDto);

    ProjectionDto updateProjection(Long projectionId, ProjectionDto projectionDto);

}
