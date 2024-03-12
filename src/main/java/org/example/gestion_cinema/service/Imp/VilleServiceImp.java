package org.example.gestion_cinema.service.Imp;

import org.example.gestion_cinema.dtos.VilleDto;
import org.example.gestion_cinema.entites.Ville;
import org.example.gestion_cinema.repository.VilleRepository;
import org.example.gestion_cinema.service.IVilleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VilleServiceImp implements IVilleService {
    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VilleDto createVille(VilleDto villeDto) {
        Ville ville = modelMapper.map(villeDto, Ville.class);
        Ville savedVille = villeRepository.save(ville);
        return modelMapper.map(savedVille, VilleDto.class);
    }

    @Override
    public VilleDto getVilleById(Long id) {
        Optional<Ville> optionalVille = villeRepository.findById(id);
        return optionalVille.map(ville -> modelMapper.map(ville, VilleDto.class)).orElse(null);
    }


    @Override
    public List<VilleDto> getAllVilles() {
        List<Ville> allVilles = villeRepository.findAll();
        return allVilles.stream().map(ville -> modelMapper.map(ville, VilleDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteVille(Long id) {
        villeRepository.deleteById(id);
    }
}
