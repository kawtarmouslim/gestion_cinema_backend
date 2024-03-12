package org.example.gestion_cinema.service.Imp;


import org.example.gestion_cinema.dtos.SeanceDto;
import org.example.gestion_cinema.entites.Seance;
import org.example.gestion_cinema.repository.SeanceRepository;
import org.example.gestion_cinema.service.ISeanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeanceServiceImpl implements ISeanceService {
    @Autowired
    private SeanceRepository seanceRepository;

   @Autowired
    private ModelMapper modelMapper;  // Assurez-vous d'avoir configuré le bean ModelMapper


    @Override
    public SeanceDto createSeance(SeanceDto seanceDto) {
        Seance seance = modelMapper.map(seanceDto, Seance.class);
        Seance savedSeance = seanceRepository.save(seance);
        return modelMapper.map(savedSeance, SeanceDto.class);
    }

    @Override
    public SeanceDto getSeanceById(Long id) {
        Optional<Seance> optionalSeance = seanceRepository.findById(id);
        return optionalSeance.map(seance -> modelMapper.map(seance, SeanceDto.class)).orElse(null);
    }

    @Override
    public List<SeanceDto> getAllSeances() {
        List<Seance> allSeances = seanceRepository.findAll();
        return allSeances.stream()
                .map(seance -> modelMapper.map(seance, SeanceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSeance(Long id) {
        seanceRepository.deleteById(id);
    }
}
