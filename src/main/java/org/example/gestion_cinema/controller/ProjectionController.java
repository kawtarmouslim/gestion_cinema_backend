package org.example.gestion_cinema.controller;

import org.example.gestion_cinema.dtos.ClientsDto;
import org.example.gestion_cinema.dtos.ProjectionDto;
import org.example.gestion_cinema.service.IProjectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projections")
public class ProjectionController {
    @Autowired
    private IProjectionService projectionService;

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping
    public ResponseEntity<List<ProjectionDto>> getAllProjection() {
        List<ProjectionDto> list = projectionService.getAllProjections();
        list.forEach(System.out::println);
        return ResponseEntity.ok(list); // Utilisez la liste déjà récupérée plutôt que de rappeler la méthode
    }

    @PostMapping
    public ResponseEntity<ProjectionDto> createProjection(@RequestBody ProjectionDto projectionDto) {
        ProjectionDto createdProjection = projectionService.createProjection(projectionDto);
        return new ResponseEntity<>(createdProjection, HttpStatus.CREATED);
    }

    @PutMapping("/{projectionId}")
    public ResponseEntity<ProjectionDto> updateProjection(
            @PathVariable Long projectionId,
            @RequestBody ProjectionDto projectionDto
    ) {
        ProjectionDto updatedProjection = projectionService.updateProjection(projectionId, projectionDto);
        return new ResponseEntity<>(updatedProjection, HttpStatus.OK);
    }

}
