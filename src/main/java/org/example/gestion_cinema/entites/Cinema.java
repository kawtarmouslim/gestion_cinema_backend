package org.example.gestion_cinema.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adresse;
    private int nombreSalles;
    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;
    //    @OneToMany(mappedBy = "cinema")
//    private Collection<Salle> salles;


}
