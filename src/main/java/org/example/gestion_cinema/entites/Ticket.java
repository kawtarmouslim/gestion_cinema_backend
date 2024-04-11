package org.example.gestion_cinema.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Ticket implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "projection_id")
    @ToString.Exclude
    private Projection projection;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Clients client;
    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", prix=" + prix +
                // Ajoutez d'autres propriétés ici si nécessaire
                '}';
    }
}



