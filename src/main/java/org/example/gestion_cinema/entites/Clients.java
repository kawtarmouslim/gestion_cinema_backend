package org.example.gestion_cinema.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clients {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String tel;
    private String email;

    public Clients(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

}
