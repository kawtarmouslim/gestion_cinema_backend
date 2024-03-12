package org.example.gestion_cinema.service.Imp;

import lombok.AllArgsConstructor;
import org.example.gestion_cinema.dtos.UtilisateurDto;
import org.example.gestion_cinema.entites.Utilisateur;
import org.example.gestion_cinema.repository.UtilisateurRepository;
import org.example.gestion_cinema.service.IUtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UtilisateurServiceImp implements IUtilisateurService {
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public List<UtilisateurDto> getAllUtilisateut() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream()
                .map(utilisateur -> modelMapper.map(utilisateur, UtilisateurDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public UtilisateurDto getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("utilisateur non trouvé avec l'ID : " + id));
        return modelMapper.map(utilisateur, UtilisateurDto.class);
    }

    @Override
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = modelMapper.map(utilisateurDto, Utilisateur.class);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return modelMapper.map(savedUtilisateur, UtilisateurDto.class);
    }

    @Override
    public UtilisateurDto updateLivre(Long id, UtilisateurDto utilisateurDto) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
        modelMapper.map(utilisateurDto, existingUtilisateur);
        Utilisateur updatedUtilisateur = utilisateurRepository.save(existingUtilisateur);
        return modelMapper.map(updatedUtilisateur, UtilisateurDto.class);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id);
        }
        utilisateurRepository.deleteById(id);

    }
}
