package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.SalleDto;
import org.example.gestion_cinema.entites.Salle;
import org.example.gestion_cinema.repository.SalleRepository;
import org.example.gestion_cinema.service.ISalleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalleService implements ISalleService {
    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SalleDto getSalleById(Long salleId) {
        Optional<Salle> salleOptional = salleRepository.findById(salleId);
        if (salleOptional.isPresent()) {
            return modelMapper.map(salleOptional.get(), SalleDto.class);
        } else {
            throw new EntityNotFoundException("Salle not found with id: " + salleId);
        }
    }

    @Override
    public List<SalleDto> getAllSalles() {
        List<Salle> salles = salleRepository.findAll();
        return salles.stream()
                .map(salle -> modelMapper.map(salle, SalleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SalleDto createSalle(SalleDto salleDto) {
        Salle salle = modelMapper.map(salleDto, Salle.class);
        Salle savedSalle = salleRepository.save(salle);
        return modelMapper.map(savedSalle, SalleDto.class);
    }

    @Override
    public SalleDto updateSalle(Long salleId, SalleDto salleDto) {
        Optional<Salle> existingSalleOptional = salleRepository.findById(salleId);
        if (existingSalleOptional.isPresent()) {
            Salle existingSalle = existingSalleOptional.get();
            existingSalle.setName(salleDto.getName());
            existingSalle.setNombrePlace(salleDto.getNombrePlace());

            Salle updatedSalle = salleRepository.save(existingSalle);
            return modelMapper.map(updatedSalle, SalleDto.class);
        } else {
            throw new EntityNotFoundException("Salle not found with id: " + salleId);
        }
    }

    @Override
    public void deleteSalle(Long salleId) {
        if (salleRepository.existsById(salleId)) {
            salleRepository.deleteById(salleId);
        } else {
            throw new EntityNotFoundException("Salle not found with id: " + salleId);
        }

    }
}
